import React, { useState, useEffect, useCallback } from "react";
import axios from "axios";
import Header from "../components/Header";
import ChatBox from "../components/Chatbox";

interface ChatBoxProps {
  walletState: string | null;
}

interface Project {
  name: string;
  description: string;
  projectStage: string;
}

interface Student {
  firstName: string;
  lastName: string;
  email: string;
  projects: Project[];
}

const Home: React.FC<ChatBoxProps> = ({ walletState }) => {
  const [students, setStudents] = useState<Student[]>([]);
  const [onChat, setonChat] = useState<any>(false);
  const fetchData = async () => {

    try {
      const response = await axios.get("http://localhost:8080/api/all-data");
      const data = response.data;

      setStudents(data.students || []);
      setonChat(false)
    } catch (error) {
      console.error("There was a problem fetching data:", error);
    }
  }

  useEffect(() => {
    fetchData();
    console.log("xxx")
  }, [onChat]);

 

  return (
    <div className="home">
      <Header />
      <div className="content">
        <ChatBox walletState={walletState} setonChat={setonChat} />
        <div className="data-sections">
          <div className="students-section">
            <h2>Students</h2>
            <ul>
              {students.map((student, index) => (
                <li key={index}>
                  <strong>
                    {student.firstName} {student.lastName}
                  </strong>{" "}
                  - {student.email}
                  <ul>
                    {student.projects.map((project, projectIndex) => (
                      <li key={projectIndex}>
                        <strong>Project:</strong> {project.name} <br />
                        <strong>Description:</strong> {project.description} <br />
                        <strong>Stage:</strong> {project.projectStage}
                      </li>
                    ))}
                  </ul>
                </li>
              ))}
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
