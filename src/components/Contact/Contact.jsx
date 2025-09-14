import ContactCard from "./ContactCard.jsx";

const contactDetails = [
    {
        type: "Email",
        value: "okiror1vinald@gmail.com",
        icon: "bi-envelope-fill",
        href: "mailto:okiror1vinald@gmail.com",
        external: false,
    },
    {
        type: "GitHub",
        value: "Vinald",
        icon: "bi-github",
        href: "https://github.com/vinald",
        external: true,
    },
    {
        type: "LinkedIn",
        value: "Okiror Samuel Vinald",
        icon: "bi-linkedin",
        href: "https://linkedin.com/in/vinald12",
        external: true,
    },
    {
        type: "Phone",
        value: "+256 746 178 844",
        icon: "bi-telephone-fill",
        href: null,
        external: false,
    },
];

const Contact = () => (
    <div className="container mt-5">
        <div className="row g-4 justify-content-center">
            {contactDetails.map((detail, idx) => (
                <div className="col-md-6 col-lg-3" key={idx}>
                    <ContactCard detail={detail} />
                </div>
            ))}
        </div>
    </div>
);

export default Contact;
