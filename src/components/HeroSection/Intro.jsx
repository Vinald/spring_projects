import resume from "../../assets/okiror_samuel_vinald.pdf";

const Intro = () => (
    <div className="bg-dark  text-center py-5 rounded-3">
        <h4 className="fw-light mb-3 text-uppercase text-secondary tracking-wider">Hello, I am</h4>
        <h1 className="display-3 fw-bold mb-3 text-gradient-primary">
            <span className="d-block">Okiror Samuel Vinald</span>
        </h1>
        <div className="mb-4">
            <h3 className="d-inline-block position-relative fw-bold mb-3 title-underline">
                <span className="text-light">Computer Engineer</span>
            </h3>
        </div>
        <div className="d-flex flex-wrap justify-content-center gap-3 mt-4">
            <a
                href={resume}
                className="btn btn-warning btn-lg rounded-pill px-4 shadow-sm fw-bold"
            >
                <i className="bi bi-download me-2"></i>Download Resume
            </a>
        </div>
    </div>
);

export default Intro;
