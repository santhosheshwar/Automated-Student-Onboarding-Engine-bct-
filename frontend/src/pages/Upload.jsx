import { useState } from "react";
import API from "../api";

export default function Upload() {
  const [file, setFile] = useState(null);

  const handleFile = (e) => {
    setFile(e.target.files[0]);
  };

  const handleUpload = () => {
    if (!file) return alert("Select file");

    const reader = new FileReader();

    reader.onload = async (e) => {
      const text = e.target.result;
      const rows = text.split("\n").slice(1);

      const students = rows
        .filter((row) => row.trim() !== "")
        .map((row) => {
          const cols = row.split(",");
          return {
            name: cols[0],
            email: cols[1],
            age: Number(cols[2]),
            department: cols[3],
            phone: cols[4],
          };
        });

      await API.post("/batch", students);
      alert("Uploaded successfully");
    };

    reader.readAsText(file);
  };

  return (
    <div>
      <h2>Upload CSV</h2>

      <input type="file" onChange={handleFile} />

      <button onClick={handleUpload}>Upload</button>
    </div>
  );
}