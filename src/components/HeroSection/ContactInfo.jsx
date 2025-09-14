import React from "react";

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

const ContactInfo = () => (
    <div className="container mt-5">
        <div className="row g-4 justify-content-center">
            {contactDetails.map((detail, idx) => (
                <div className="col-md-6 col-lg-3" key={idx}>
                    {detail.href ? (
                        <a
                            href={detail.href}
                            className="text-warning text-decoration-none"
                            {...(detail.external
                                ? { target: "_blank", rel: "noopener noreferrer" }
                                : {})}
                        >
                            <div className="card bg-dark h-100 border-0 shadow-sm">
                                <div className="card-body text-center p-4">
                                    <div className="icon-lg bg-warning-soft text-warning rounded-circle mb-3 mx-auto">
                                        <i className={`bi ${detail.icon}`}></i>
                                    </div>
                                    <h5 className="text-light mb-2">{detail.type}</h5>
                                    <span className="text-warning">{detail.value}</span>
                                </div>
                            </div>
                        </a>
                    ) : (
                        <div className="card bg-dark h-100 border-0 shadow-sm">
                            <div className="card-body text-center p-4">
                                <div className="icon-lg bg-warning-soft text-warning rounded-circle mb-3 mx-auto">
                                    <i className={`bi ${detail.icon}`}></i>
                                </div>
                                <h5 className="text-light mb-2">{detail.type}</h5>
                                <span className="text-warning">{detail.value}</span>
                            </div>
                        </div>
                    )}
                </div>
            ))}
        </div>
    </div>
);

export default ContactInfo;
