import os
import sys
import threading
import time

sys.path.insert(0, os.path.dirname(__file__))

from generator import generate_students
from validator import validate_students
from converter import convert_to_json
from sender import send_in_batches
from watcher import start_watching

def main():
    os.makedirs("data", exist_ok=True)

    filepath = generate_students(150)
    print(f"Generated students file: {filepath}")

    valid_records, invalid_records = validate_students(filepath)
    print(f"Valid records: {len(valid_records)}, Invalid records: {len(invalid_records)}")

    convert_to_json(valid_records)
    print(f"Converted {len(valid_records)} records to JSON")

   
    print("Waiting for Service B to start...")
    time.sleep(20)

    send_in_batches(valid_records)
    print(f"Sent {len(valid_records)} records in batches")

    watcher_thread = threading.Thread(
        target=start_watching,
        args=("data",),
        daemon=True
    )
    watcher_thread.start()

    try:
        while True:
            time.sleep(1)
    except KeyboardInterrupt:
        pass

if __name__ == "__main__":
    main()