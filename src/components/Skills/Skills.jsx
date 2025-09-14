import SkillsCard from "./SkillCard";
const technicalSkills = [
    {
        title: "Programming Languages",
        icon: "fas fa-code",
        skills: [
            "Python", "Java", "Kotlin", "JavaScript", "TypeScript", "PHP", "SQL", "HTML/CSS"
        ]
    },
    {
        title: "Web Development",
        icon: "fas fa-globe",
        skills: [
            "React", "Node.js", "Express.js", "Next.js", "Laravel", "Django", "Spring Boot",
            "Bootstrap", "APIs", "UI/UX", "Responsive Design"
        ]
    },
    {
        title: "Databases",
        icon: "fas fa-database",
        skills: [
            "MySQL", "PostgreSQL", "MongoDB"
        ]
    },
    {
        title: "Mobile Development",
        icon: "fas fa-mobile-alt",
        skills: [
            "Kotlin", "Java", "React Native"
        ]
    },
    {
        title: "Machine Learning & AI",
        icon: "fas fa-brain",
        skills: [
            "TensorFlow", "Scikit-learn", "Data Analysis", "Deep Learning",
            "Feature Engineering", "LLM", "Generative AI"
        ]
    },
    {
        title: "Embedded Systems",
        icon: "fas fa-tools",
        skills: [
            "Microcontroller programming", "C/C++ and Python", "Arduino",
            "Raspberry Pi", "ESP32", "Sensors", "Actuators"
        ]
    },
    {
        title: "Tools & Others",
        icon: "fas fa-tools",
        skills: [
            "Git/GitHub", "Linux", "NPM", "PIP", "Figma", "Docker", "Trello",
            "Jira", "Illustrator", "Photoshop", "Networking"
        ]
    }
];

const softSkills = [
    {
        title: "Core Skills",
        icon: "fas fa-tools",
        skills: [
            "Problem-Solving", "Teamwork"
        ]
    },
    {
        title: "Interpersonal Skills",
        icon: "fas fa-tools",
        skills: [
            "Adaptability", "Time Management"
        ]
    },
    {
        title: "Others",
        icon: "fas fa-tools",
        skills: [
            "Communication", "Quick Learner"
        ]
    }
];

const Skills = () => (
    <section id="skills" className="rounded-3 container mb-5 py-5 bg-dark text-light">
        <div className="container">
            <h2 className="text-center mb-5 text-uppercase fw-bold">Skills</h2>
            <h4 className="mb-4 text-warning fw-bold">Technical Skills</h4>
            <div className="row g-4">
                {technicalSkills.map((category, idx) => (
                    <SkillsCard
                        key={category.title}
                        iconClass={category.icon}
                        title={category.title}
                        skills={category.skills}
                    />
                ))}
            </div>
            <h4 className="mt-5 text-warning fw-bold">Soft Skills</h4>
            <div className="row g-4">
                {softSkills.map((category, idx) => (
                    <SkillsCard
                        key={category.title}
                        iconClass={category.icon}
                        title={category.title}
                        skills={category.skills}
                    />
                ))}
            </div>
        </div>
    </section>
);

export default Skills;
