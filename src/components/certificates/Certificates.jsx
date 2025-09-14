import CertificateCard from "./CertificateCard";

const certificates = [
    {
        title: "Python",
        issuer: "Issued by CISCO - April 2025",
        link: "https://www.credly.com/badges/2dc786ff-1ccc-4d14-9221-229d41cd66f4/public_url",
    },
    {
        title: "Python, AI, Robotics and Embedded Systems",
        issuer: "Issued by netLabs!UG - August 2023",
        link: "assets/images/netlabsugCertificate.png",
    },
    {
        title: "Project Management",
        issuer: "Issued by Project Management Institute - April 2024",
        link: "assets/images/ProjectManagementCertificate.png",
    },
    {
        title: "Social-Behavioral-Educational (SBE) Comprehensive",
        issuer: "Issued by CITI - April 2025",
        link: "https://www.citiprogram.org/verify/?w64eacb8b-d6bc-46fd-94d5-b1dea78e5b19-68870613",
    },
    {
        title: "Anti-Sexual Harassment",
        issuer: "Issued by INFECTIOUS DISEASES INSTITUTE - Feb 2025",
        link: "https://elearning.idi.co.ug/courses/mod/customcert/view.php?id=4149&downloadown=1",
    },
    {
        title: "Biomedical Data or Specimens Only Research",
        issuer: "Issued by CITI - Feb 2025",
        link: "https://www.citiprogram.org/verify/?wfbc376b8-e4af-456a-9993-bef4add2f037-67625164",
    },
    {
        title: "GCP – Social and Behavioral Research Best Practices",
        issuer: "Issued by CITI - Feb 2025",
        link: "https://www.citiprogram.org/verify/?wf3a78f01-e117-4699-b07f-a97a08ff699b-67625163",
    },
    {
        title: "UIPE Member",
        issuer: "Issued by Uganda Institution of Professional Engineers UIPE - August 2023",
        link: "assets/images/UIPECertificate.png",
    },
];

const Certificates = () => (
    <section id="certificates" className="container py-5 bg-dark text-light">
        <div className="container">
            <h2 className="text-center mb-5 text-uppercase fw-bold">Certificates</h2>
            <div className="row g-4">
                {certificates.map((certificate, idx) => (
                    <CertificateCard key={idx} certificate={certificate} />
                ))}
            </div>
        </div>
    </section>
);

export default Certificates;
