import profileImage from "../../assets/images/profile_new_1.jpg";

const ProfileSummary = () => {
    return (
        <div className=" row align-items-center justify-content-center pt-5">
            <div className="col-lg-4 text-center mb-4 mb-md-0">
                <img
                    src={profileImage}
                    alt="Vinald"
                    className="img-fluid rounded-5 shadow-lg"
                    style={{ maxWidth: "300px", border: "5px solid black" }}
                />
            </div>
            <div className="col-lg-8 text-light">
                <p className="lead my-4 text-light">
                    I'm a Results-driven <strong>Software Developer</strong> with a Bachelor of
                    Science in Computer Engineering, specializing in full-stack web and mobile
                    development. With professional expertise in
                    <strong>JavaScript</strong> (React and React Native),
                    <strong>Python</strong> (Django), and <strong>Java</strong>, with hands-on
                    experience building scalable, user-centric applications.
                </p>
                <p className="mb-4">
                    Since transitioning from academia to professional development, I've focused on
                    bridging the gap between theoretical knowledge and practical implementation. My
                    engineering background gives me a unique perspective on system design, while my
                    hands-on experience allows me to create efficient, scalable applications that
                    solve real business problems.
                </p>
                <p className="mb-4">
                    What drives me is the opportunity to transform complex requirements into
                    intuitive digital experiences. Whether optimizing backend performance or
                    crafting pixel-perfect UIs, I approach each challenge with a problem-solving
                    mindset and commitment to clean, maintainable code.
                </p>
                <h5 className="text-warning mb-3">
                    Software Development | Machine Learning & AI | Cyber Security Enthusiast |
                    Continuous Learner
                </h5>
            </div>
        </div>
    );
};

export default ProfileSummary;
