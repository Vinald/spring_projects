const CertificateCard = ({ certificate }) => (
    <div className="col-xxl-3 col-lg-4 col-md-6">
        <div className="card h-100 bg-secondary text-light border-0 shadow-sm">
            <div className="card-body d-flex flex-column">
                <h5 className="card-title text-warning mb-3">{certificate.title}</h5>
                <p className="card-text mb-4">{certificate.issuer}</p>
                <a
                    href={certificate.link}
                    className="btn btn-outline-light btn-sm mt-auto align-self-start"
                    target="_blank"
                    rel="noopener"
                >
                    View Certificate
                </a>
            </div>
        </div>
    </div>
);

export default CertificateCard;
