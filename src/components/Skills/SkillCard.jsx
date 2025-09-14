const SkillsCard = ({ iconClass, title, skills }) => (
    <div className="col-xl-4 col-lg-6 col-md-6">
        <div className="card h-100 bg-secondary">
            <div className="card-body">
                <h5 className="card-title text-warning fw-bold">
                    <i className={`${iconClass} me-2`}></i>{title}
                </h5>
                <div className="d-flex flex-wrap gap-2 mt-3 lead">
                    {skills.map((skill, idx) => (
                        <span key={idx} className="badge bg-warning text-dark">
                            {skill}
                        </span>
                    ))}
                </div>
            </div>
        </div>
    </div>
);

export default SkillsCard;
