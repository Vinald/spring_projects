const ContactCard = ({ detail }) => (
    detail.href ? (
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
    )
);

export default ContactCard;