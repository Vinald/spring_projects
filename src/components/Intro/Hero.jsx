import Intro from "./Intro.jsx";
import ProfileSummary from "./ProfileSummary.jsx";

const Hero = () => (
    <section id="intro" className="container text-light py-5">
        {/* Intro section */}
        <Intro />
        {/* Profile Summary section */}
        <ProfileSummary />
    </section>
);

export default Hero;
