import json
import os

def convert_to_json(valid_records, output_path="data/students.json"):
    os.makedirs("data", exist_ok=True)

    with open(output_path, "w") as f:
        json.dump(valid_records, f, indent=4)

    return valid_records