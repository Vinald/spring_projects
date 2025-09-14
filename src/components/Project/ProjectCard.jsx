import React from "react";

const ProjectCard = ({ project }) => (
    <div className="col">
        <div className="card bg-dark text-light h-100">
            <img
                src={project.image}
                className="bg-white card-img-top img-fluid rounded-3"
                alt={project.title}
                style={{ height: "200px", objectFit: "cover" }}
            />
            <div className="card-body">
                <h3 className="card-title">{project.title}</h3>
                <p className="card-text">{project.description}</p>
            </div>
            <div className="card-footer">
                <a
                    href={project.link}
                    className={project.disabled ? "btn btn-info disabled" : "btn btn-info"}
                >
                    Read More
                </a>
            </div>
        </div>
    </div>
);

export default ProjectCard;
