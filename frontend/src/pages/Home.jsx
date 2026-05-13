import { useEffect, useState } from "react";
import API from "../api";

export default function Home() {
  const [students, setStudents] = useState([]);

  useEffect(() => {
  API.get("")
    .then(res => setStudents(res.data))
    .catch(err => console.log(err));
}, []);

  return (
    <div>
      <h2>All Students</h2>
      <table border="1">
        <thead>
          <tr>
            <th>ID</th><th>Name</th><th>Email</th>
            <th>Age</th><th>Dept</th><th>Phone</th>
          </tr>
        </thead>
        <tbody>
          {students.map(s => (
            <tr key={s.id}>
              <td>{s.id}</td>
              <td>{s.name}</td>
              <td>{s.email}</td>
              <td>{s.age}</td>
              <td>{s.department}</td>
              <td>{s.phone}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}