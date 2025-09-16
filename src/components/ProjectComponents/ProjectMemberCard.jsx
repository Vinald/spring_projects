const ProjectMemberCard = ({ name, role, imageSrc }) => {
    return (
        <div className="card text-center">
            <img
                src={imageSrc}
                alt={`Team Member ${name}`}
                className="card-img-top rounded-5 img-fluid mb-3 profile"
            />
            <div className="card-body">
                <h5 className="card-title">{name}</h5>
                <p className="card-text">{role}</p>
            </div>
        </div>
    );
}

export default ProjectMemberCard;