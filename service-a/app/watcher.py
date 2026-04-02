import time
import os
import sys

sys.path.insert(0, os.path.dirname(__file__))

from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
from validator import validate_students
from converter import convert_to_json
from sender import send_in_batches

class CSVHandler(FileSystemEventHandler):

    def on_created(self, event):
        if event.src_path.endswith(".csv"):
            time.sleep(1)
            self.process(event.src_path)

    def on_modified(self, event):
        if event.src_path.endswith(".csv"):
            time.sleep(1)
            self.process(event.src_path)

    def process(self, filepath):
        valid_records, invalid_records = validate_students(filepath)

        if not valid_records:
            return

        convert_to_json(valid_records)
        send_in_batches(valid_records)


def start_watching(directory="data"):
    os.makedirs(directory, exist_ok=True)

    event_handler = CSVHandler()
    observer = Observer()
    observer.schedule(event_handler, path=directory, recursive=False)
    observer.start()

    try:
        while True:
            time.sleep(2)
    except KeyboardInterrupt:
        observer.stop()
    observer.join()