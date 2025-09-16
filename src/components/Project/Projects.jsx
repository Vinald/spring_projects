import ProjectCard from "./ProjectCard";
import irrigationPic from "../../assets/images/irrigation.jpg";
import wheelchairPic from '../../assets/images/wheelchair.jpg'
import vxnaidPic from '../../assets/images/ic_vxnaid_logo.png'
import AgriwaveProject from "../../pages/AgriwaveProject/Agriwave";
 import VoiceControlProject from "../../pages/LugandaVoiceControlledSystem/VoiceControl";

const projects = [
    {
        image: irrigationPic,
        title: "Smart Irrigation System",
        description:
            "Developed a smart irrigation system integrating IoT and machine learning for real-time water management, cost reduction, and crop yield improvement. This innovative solution promotes sustainable farming practices.",
        link: "/agriwave", // Use a string path for routing
        disabled: false,
    },
    {
        image: wheelchairPic,
        title: "Voice-Controlled Wheelchair System",
        description:
            "Engineered a Luganda Voice-Controlled Wheelchair System to enhance mobility for Luganda-speaking individuals. The system integrates Luganda voice commands with speech intent recognition technology for wheelchair control.",
        link: "/luganda-voice-control", // Use a string path for routing
        disabled: false,
    },
    {
        image: vxnaidPic,
        title: "VXNAID — Vaccine and Immunization System",
        description:
            "Developed and debugged frontend and backend workflows including child registration, return visit flows, offline sync, and visit type consistency. Set up local development servers, staging servers, and tablets. Improved system usability, mobile interface, and reporting features for administrators and operators. Deployed and trained health workers across multiple facilities.",
        link: "projects/wheelchair-project.html",
        disabled: true,
    }
];

const Projects = () => (
    <section id="projects" className="container mb-4">
        <h2 className="text-center mb-4 text-uppercase fw-bold text-white">Projects</h2>
        <div className="row row-cols-1 row-cols-md-2 g-4">
            {projects.map((project, idx) => (
                <ProjectCard key={idx} project={project} />
            ))}
        </div>
    </section>
);

export default Projects;
