import { useState } from "react";
import API from "../api";

export default function Search() {
  const [id, setId] = useState("");
  const [student, setStudent] = useState(null);

  const handleSearch = () => {
    API.get(`/${id}`)
      .then((res) => setStudent(res.data))
      .catch(() => alert("Student not found"));
  };

  return (
    <div>
      <h2>Search Student</h2>

      <input
        type="number"
        placeholder="Enter ID"
        value={id}
        onChange={(e) => setId(e.target.value)}
      />

      <button onClick={handleSearch}>Search</button>

      {student && (
        <div>
          <p>Name: {student.name}</p>
          <p>Email: {student.email}</p>
          <p>Age: {student.age}</p>
          <p>Department: {student.department}</p>
          <p>Phone: {student.phone}</p>
        </div>
      )}
    </div>
  );
}