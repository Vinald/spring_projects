import React from "react";

const Skills = () => (
    <section id="skills" className="container mb-5 py-5 bg-dark text-light">
        <div className="container">
            <h2 className="text-center mb-5 text-uppercase fw-bold">Skills</h2>
            <h4 className="mb-4 text-warning fw-bold">Technical Skills</h4>
            <div className="row g-4">
                {/* Programming Languages */}
                <div className="col-xl-4 col-lg-6 col-md-6">
                    <div className="card h-100 bg-secondary">
                        <div className="card-body">
                            <h5 className="card-title text-warning fw-bold">
                                <i className="fas fa-code me-2"></i>Programming Languages
                            </h5>
                            <div className="d-flex flex-wrap gap-2 mt-3 lead">
                                <span className="badge bg-warning text-dark">Python</span>
                                <span className="badge bg-warning text-dark">Java</span>
                                <span className="badge bg-warning text-dark">Kotlin</span>
                                <span className="badge bg-warning text-dark">JavaScript</span>
                                <span className="badge bg-warning text-dark">TypeScript</span>
                                <span className="badge bg-warning text-dark">PHP</span>
                                <span className="badge bg-warning text-dark">SQL</span>
                                <span className="badge bg-warning text-dark">HTML/CSS</span>
                            </div>
                        </div>
                    </div>
                </div>
                {/* Web Development */}
                <div className="col-xl-4 col-lg-6 col-md-6">
                    <div className="card h-100 bg-secondary">
                        <div className="card-body">
                            <h5 className="card-title text-warning fw-bold">
                                <i className="fas fa-globe me-2"></i>Web Development
                            </h5>
                            <div className="d-flex flex-wrap gap-2 mt-3 lead">
                                <span className="badge bg-warning text-dark">React</span>
                                <span className="badge bg-warning text-dark">Node.js</span>
                                <span className="badge bg-warning text-dark">Express.js</span>
                                <span className="badge bg-warning text-dark">Next.js</span>
                                <span className="badge bg-warning text-dark">Laravel</span>
                                <span className="badge bg-warning text-dark">Django</span>
                                <span className="badge bg-warning text-dark">Spring Boot</span>
                                <span className="badge bg-warning text-dark">Bootstrap</span>
                                <span className="badge bg-warning text-dark">APIs</span>
                                <span className="badge bg-warning text-dark">UI/UX</span>
                                <span className="badge bg-warning text-dark">
                                    Responsive Design
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
                {/* Databases */}
                <div className="col-xl-4 col-lg-6 col-md-6">
                    <div className="card h-100 bg-secondary">
                        <div className="card-body">
                            <h5 className="card-title text-warning fw-bold">
                                <i className="fas fa-database me-2"></i>Databases
                            </h5>
                            <div className="d-flex flex-wrap gap-2 mt-3 lead">
                                <span className="badge bg-warning text-dark">MySQL</span>
                                <span className="badge bg-warning text-dark">PostgreSQL</span>
                                <span className="badge bg-warning text-dark">MongoDB</span>
                            </div>
                        </div>
                    </div>
                </div>
                {/* Mobile Development */}
                <div className="col-xl-4 col-lg-6 col-md-6">
                    <div className="card h-100 bg-secondary">
                        <div className="card-body">
                            <h5 className="card-title text-warning fw-bold">
                                <i className="fas fa-mobile-alt me-2"></i>Mobile Development
                            </h5>
                            <div className="d-flex flex-wrap gap-2 mt-3 lead">
                                <span className="badge bg-warning text-dark">Kotlin</span>
                                <span className="badge bg-warning text-dark">Java</span>
                                <span className="badge bg-warning text-dark">React Native</span>
                            </div>
                        </div>
                    </div>
                </div>
                {/* Machine Learning & AI */}
                <div className="col-xl-4 col-lg-6 col-md-6">
                    <div className="card h-100 bg-secondary">
                        <div className="card-body">
                            <h5 className="card-title text-warning fw-bold">
                                <i className="fas fa-brain me-2"></i>Machine Learning & AI
                            </h5>
                            <div className="d-flex flex-wrap gap-2 mt-3 lead">
                                <span className="badge bg-warning text-dark">TensorFlow</span>
                                <span className="badge bg-warning text-dark">Scikit-learn</span>
                                <span className="badge bg-warning text-dark">Data Analysis</span>
                                <span className="badge bg-warning text-dark">Deep Learning</span>
                                <span className="badge bg-warning text-dark">
                                    Feature Engineering
                                </span>
                                <span className="badge bg-warning text-dark">LLM</span>
                                <span className="badge bg-warning text-dark">Generative AI</span>
                            </div>
                        </div>
                    </div>
                </div>
                {/* Embedded Systems */}
                <div className="col-xl-4 col-lg-6 col-md-6">
                    <div className="card h-100 bg-secondary">
                        <div className="card-body">
                            <h5 className="card-title text-warning fw-bold">
                                <i className="fas fa-tools me-2"></i>Embedded Systems
                            </h5>
                            <div className="d-flex flex-wrap gap-2 mt-3 lead">
                                <span className="badge bg-warning text-dark">
                                    Microcontroller programming
                                </span>
                                <span className="badge bg-warning text-dark">C/C++ and Python</span>
                                <span className="badge bg-warning text-dark">Arduino</span>
                                <span className="badge bg-warning text-dark">Raspberry Pi</span>
                                <span className="badge bg-warning text-dark">ESP32</span>
                                <span className="badge bg-warning text-dark">Sensors</span>
                                <span className="badge bg-warning text-dark">Actuators</span>
                            </div>
                        </div>
                    </div>
                </div>
                {/* Tools & Others */}
                <div className="col-xl-4 col-lg-6 col-md-6">
                    <div className="card h-100 bg-secondary">
                        <div className="card-body">
                            <h5 className="card-title text-warning fw-bold">
                                <i className="fas fa-tools me-2"></i>Tools & Others
                            </h5>
                            <div className="d-flex flex-wrap gap-2 mt-3 lead">
                                <span className="badge bg-warning text-dark">Git/GitHub</span>
                                <span className="badge bg-warning text-dark">Linux</span>
                                <span className="badge bg-warning text-dark">NPM</span>
                                <span className="badge bg-warning text-dark">PIP</span>
                                <span className="badge bg-warning text-dark">Figma</span>
                                <span className="badge bg-warning text-dark">Docker</span>
                                <span className="badge bg-warning text-dark">Trello</span>
                                <span className="badge bg-warning text-dark">Jira</span>
                                <span className="badge bg-warning text-dark">Illustrator</span>
                                <span className="badge bg-warning text-dark">Photoshop</span>
                                <span className="badge bg-warning text-dark">Networking</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <h4 className="mt-5 text-warning fw-bold">Soft Skills</h4>
            <div className="row g-4">
                {/* Core Skills */}
                <div className="col-xl-4 col-lg-6 col-md-6">
                    <div className="card h-100 bg-secondary">
                        <div className="card-body">
                            <h5 className="card-title text-warning fw-bold">
                                <i className="fas fa-tools me-2"></i>Core Skills
                            </h5>
                            <div className="d-flex flex-wrap gap-2 mt-3 lead">
                                <span className="badge bg-warning text-dark">Problem-Solving</span>
                                <span className="badge bg-warning text-dark">Teamwork</span>
                            </div>
                        </div>
                    </div>
                </div>
                {/* Interpersonal Skills */}
                <div className="col-xl-4 col-lg-6 col-md-6">
                    <div className="card h-100 bg-secondary">
                        <div className="card-body">
                            <h5 className="card-title text-warning fw-bold">
                                <i className="fas fa-tools me-2"></i>Interpersonal Skills
                            </h5>
                            <div className="d-flex flex-wrap gap-2 mt-3 lead">
                                <span className="badge bg-warning text-dark">Adaptability</span>
                                <span className="badge bg-warning text-dark">Time Management</span>
                            </div>
                        </div>
                    </div>
                </div>
                {/* Others */}
                <div className="col-xl-4 col-lg-6 col-md-6">
                    <div className="card h-100 bg-secondary">
                        <div className="card-body">
                            <h5 className="card-title text-warning fw-bold">
                                <i className="fas fa-tools me-2"></i>Others
                            </h5>
                            <div className="d-flex flex-wrap gap-2 mt-3 lead">
                                <span className="badge bg-warning text-dark">Communication</span>
                                <span className="badge bg-warning text-dark">Quick Learner</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
);

export default Skills;
