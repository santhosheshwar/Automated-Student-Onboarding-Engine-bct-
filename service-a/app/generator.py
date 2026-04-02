import pandas as pd
import random
from faker import Faker

fake = Faker()

def generate_students(num_records=150):
    students = []

    for i in range(num_records):
        record = {
            "name": fake.name(),
            "email": fake.email(),
            "age": random.randint(18, 30),
            "department": random.choice([
                "Computer Science",
                "Mechanical",
                "Electrical",
                "Civil",
                "Electronics"
            ]),
            "phone": fake.phone_number()
        }

        if i % 10 == 0:
            record["email"] = ""

        elif i % 13 == 0:
            record["email"] = "invalidemail@"

        elif i % 17 == 0:
            record["name"] = ""

        elif i % 19 == 0:
            record["age"] = -5

        elif i % 23 == 0:
            record["age"] = "twenty"

        students.append(record)

    df = pd.DataFrame(students)
    filepath = "data/students.csv"
    df.to_csv(filepath, index=False)
    return filepath