import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import Home from "./pages/Home";
import Search from "./pages/Search";
import Upload from "./pages/Upload";

function App() {
  return (
    <BrowserRouter>
      <div style={{ fontFamily: "Arial", padding: "20px" }}>

        <h2>Student Onboarding System</h2>

        
        <nav style={{ marginBottom: "20px" }}>
          <Link to="/" style={{ marginRight: "10px" }}>Home</Link>
          <Link to="/search" style={{ marginRight: "10px" }}>Search</Link>
          <Link to="/upload">Upload</Link>
        </nav>

        
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/search" element={<Search />} />
          <Route path="/upload" element={<Upload />} />
        </Routes>

      </div>
    </BrowserRouter>
  );
}

export default App;