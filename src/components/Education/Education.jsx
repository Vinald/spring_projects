import EducationCard from "./EducationCard";

const educationList = [
    {
        date: "2021 - 2025",
        degree: "Bachelor of Science in Computer Engineering",
        school: "Makerere University, Kampala-Uganda",
    },
    {
        date: "2018 - 2019",
        degree: "Uganda Advanced Certificate of Education",
        school: "St. Peter's College, Tororo",
    },
    {
        date: "2014 - 2017",
        degree: "Uganda Certificate of Education",
        school: "St. Peter's College, Tororo",
    },
];

const Education = () => (
    <section id="education" className="container mb-4 text-light">
        <h2 className="text-center mb-4 text-uppercase fw-bold">Education</h2>
        <div className="timeline">
            {educationList.map((edu, idx) => (
                <EducationCard key={idx} education={edu} />
            ))}
        </div>
    </section>
);

export default Education;
