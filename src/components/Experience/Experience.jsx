import ExperienceCard from "./ExperienceCard";

const experiences = [
    {
        date: "January 2025 - Present",
        title: "Software Developer, Infectious Disease Institute",
        skills: "Agile, Mobile App Development (Kotlin), Docker, OpenMRS, Team work, Documentation",
        responsibilities: [
            "Collaborated with cross-functional teams to develop and maintain mobile applications for healthcare solutions.",
            "Participated in Agile development processes, including sprint planning and daily stand-ups.",
            "Developed and integrated features into the OpenMRS platform, enhancing its functionality and user experience.",
            "Conducted code reviews and provided constructive feedback to team members.",
            "Assisted in troubleshooting and debugging issues in existing applications.",
        ],
    },
    {
        date: "August 2024 - October 2024",
        title: "Data Science and Web Development Intern, Zidio Development",
        skills: "Python, JavaScript, SQL, Excel, API Design, Data Analysis",
        responsibilities: [
            "Analyzed project data and created summaries to support decision-making.",
            "Cleaned and transformed data, ensuring high-quality datasets.",
            "Used Excel functions to enhance performance monitoring and reporting accuracy.",
            "Collaborated on data analysis tasks to identify trends and produce insights for business strategy.",
            "Developed backend services with JavaScript, optimizing server performance and scalability.",
            "Designed APIs and ensured secure integration of databases for improved functionality.",
        ],
    },
    {
        date: "April 2024 - September 2024",
        title: "Freelance Software Developer, Biremedia (Part-time)",
        skills: "Django, REST APIs, Python programming, Django REST Framework, Back-End Web Development",
        responsibilities: [
            "Designed and developed back-end services using Django and Django REST Framework, ensuring scalability and performance.",
            "Built and integrated REST APIs for seamless data exchange between client and server.",
            "Collaborated with front-end developers to deliver complete solutions for clients.",
            "Managed multiple projects while adhering to deadlines and code quality standards.",
            "Provided technical support and implemented client-driven enhancements.",
        ],
    },
    {
        date: "June 2023 - September 2023",
        title: "Software Intern, NetLabsUG",
        skills: "Python, Networking, Machine Learning, Embedded Systems",
        responsibilities: [
            "Learned foundational machine learning concepts, including data preprocessing, model selection, and evaluation.",
            "Gained a solid understanding of supervised learning algorithms (Sklearn and Tensorflow algorithms)",
            "Designed a smart irrigation management system using Python and TensorFlow that optimized irrigation patterns from real-time sensor data for better water efficiency and crop yield.",
            "Integrated sensor data into an embedded system for real-time decision-making.",
            "Acquired practical experience in embedded systems, networking, Python, and machine learning.",
        ],
    },
    {
        date: "June 2022 - September 2022",
        title: "Software Intern, EvoTech Solutions (Pivadel)",
        skills: "HTML, CSS, JavaScript, React, SEO",
        responsibilities: [
            "Developed user interface components for a school management system using React.",
            "Implemented interactive features that enhanced the user experience and functionality of the system.",
            "Generated content for the system, improving information accessibility for users.",
            "Collaborated with the team to optimize code for better performance.",
        ],
    },
    {
        date: "October 2021 - May 2023",
        title: "Graphic Designer, BireMedia",
        skills: "Illustrator, Photoshop, Color Theory",
        responsibilities: [
            "Designed marketing materials, including brochures and banners, and social media graphics, ensuring brand consistency and visual appeal.",
            "Collaborated with clients to deliver high-quality design solutions tailored to their needs.",
            "Managed multiple design projects while meeting tight deadlines.",
        ],
    },
];

const Experience = () => (
    <section id="experience" className="rounded-3 container p-4 bg-dark text-light">
        <div className="container">
            <h2 className="text-center mb-5 text-uppercase fw-bold">Experience</h2>
            <div>
                {experiences.map((exp, idx) => (
                    <ExperienceCard key={idx} experience={exp} />
                ))}
            </div>
        </div>
    </section>
);

export default Experience;
