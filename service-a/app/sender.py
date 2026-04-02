import requests
import json
import time

SERVICE_B_URL = "http://localhost:8080/api/students/batch"
BATCH_SIZE = 20

def send_in_batches(valid_records):
    total = len(valid_records)
    success_count = 0
    failed_count = 0

    for i in range(0, total, BATCH_SIZE):
        batch = valid_records[i:i + BATCH_SIZE]
        batch_number = i // BATCH_SIZE + 1
        json_payload = json.dumps(batch)

        try:
            response = requests.post(
                SERVICE_B_URL,
                data=json_payload,
                headers={"Content-Type": "application/json"},
                timeout=10
            )

            if response.status_code == 201:
                success_count += len(batch)
            else:
                failed_count += len(batch)

        except requests.exceptions.ConnectionError:
            failed_count += len(batch)

        except requests.exceptions.Timeout:
            failed_count += len(batch)

        time.sleep(0.3)