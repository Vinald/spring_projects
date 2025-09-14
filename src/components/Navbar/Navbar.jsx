import styles from "./Navbar.module.css";

const navLinks = [
    { name: "About", path: "#intro" },
    { name: "Experience", path: "#experience" },
    { name: "Education", path: "#education" },
    { name: "Skills", path: "#skills" },
    { name: "Projects", path: "#projects" },
    { name: "Certificates", path: "#certificates" },
];

const Navbar = () => {
    const currentHash = window.location.hash || "#intro";
    return (
        <nav className={`navbar navbar-expand-md navbar-light bg-light sticky-top shadow-sm ${styles.navar}`}>
            <div className="container">
                <a className={`navbar-brand fw-bold ${styles.navbarBrand}`} href="/public">
                    OKIROR SAMUEL VINALD
                </a>
                <button
                    className={`navbar-toggler ${styles.navbarToggler}`}
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav ms-auto">
                        {navLinks.map((link) => (
                            <li className="nav-item" key={link.path}>
                                <a
                                    className={`nav-link${
                                        currentHash === link.path ? " active" : ""
                                    }`}
                                    href={link.path}
                                    aria-current={currentHash === link.path ? "page" : undefined}
                                >
                                    {link.name}
                                </a>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
