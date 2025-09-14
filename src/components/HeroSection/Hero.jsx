import Intro from "./Intro.jsx";
import ProfileSummary from "./ProfileSummary.jsx";
import ContactInfo from "./ContactInfo.jsx";

const Hero = () => (
    <section id="intro" className="container text-light py-5">
        {/* HeroSection section */}
        <Intro />
        {/* Profile Summary section */}
        <ProfileSummary />
        {/* Contact Information section */}
        <ContactInfo />
    </section>
);

export default Hero;
