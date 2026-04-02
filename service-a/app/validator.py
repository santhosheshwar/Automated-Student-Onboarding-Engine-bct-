import pandas as pd
import re

def is_valid_email(email):
    pattern = r'^[\w\.-]+@[\w\.-]+\.\w+$'
    return re.match(pattern, str(email)) is not None

def validate_students(filepath):
    df = pd.read_csv(filepath)

    valid_records = []
    invalid_records = []

    for index, row in df.iterrows():
        errors = []

        if pd.isna(row["name"]) or str(row["name"]).strip() == "":
            errors.append("Missing name")

        if pd.isna(row["email"]) or str(row["email"]).strip() == "":
            errors.append("Missing email")
        elif not is_valid_email(str(row["email"])):
            errors.append(f"Invalid email: {row['email']}")

        try:
            age = int(row["age"])
            if age < 0 or age > 100:
                errors.append(f"Invalid age: {row['age']}")
        except (ValueError, TypeError):
            errors.append(f"Age not a number: {row['age']}")

        if pd.isna(row["department"]) or str(row["department"]).strip() == "":
            errors.append("Missing department")

        if errors:
            invalid_records.append({
                "row": index + 1,
                "errors": errors,
                "data": row.to_dict()
            })
        else:
            valid_records.append({
                "name": str(row["name"]).strip(),
                "email": str(row["email"]).strip(),
                "age": int(row["age"]),
                "department": str(row["department"]).strip(),
                "phone": str(row["phone"]).strip()
            })

    return valid_records, invalid_records