import ProjectCard from "./ProjectCard";
import irrigationPic from "../../assets/images/irrigation.jpg";
import wheelchairPic from '../../assets/images/wheelchair.jpg'
import blogPic from '../../assets/images/blog.jpg'
import travelloPic from '../../assets/images/trevallo.png'

const projects = [
    {
        image: irrigationPic,
        title: "Smart Irrigation System",
        description:
            "Developed a smart irrigation system integrating IoT and machine learning for real-time water management, cost reduction, and crop yield improvement. This innovative solution promotes sustainable farming practices.",
        link: "projects/irrigation-project.html",
        disabled: false,
    },
    {
        image: wheelchairPic,
        title: "Voice-Controlled Wheelchair System",
        description:
            "Engineered a Luganda Voice-Controlled Wheelchair System to enhance mobility for Luganda-speaking individuals. The system integrates Luganda voice commands with speech intent recognition technology for wheelchair control.",
        link: "projects/wheelchair-project.html",
        disabled: false,
    },
    {
        image: blogPic,
        title: "Blog Website",
        description:
            "Developed a blog platform with features for creating, editing, and deleting posts. The interface is responsive and user-friendly, utilizing Django, HTML, and Bootstrap for seamless user interaction.",
        link: "projects/wheelchair-project.html",
        disabled: true,
    },
    {
        image: travelloPic,
        title: "Travello Web Application",
        description: "Coming soon...",
        link: "projects/wheelchair-project.html",
        disabled: true,
    },
];

const Projects = () => (
    <section id="projects" className="container mb-4">
        <h2 className="text-center mb-4 text-uppercase fw-bold">Projects</h2>
        <div className="row row-cols-1 row-cols-md-2 g-4">
            {projects.map((project, idx) => (
                <ProjectCard key={idx} project={project} />
            ))}
        </div>
    </section>
);

export default Projects;
