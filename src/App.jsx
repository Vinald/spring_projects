import './App.css'
import './index.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import Hero from './components/Intro/Hero.jsx'
import Footer from "./components/Footer.jsx";
import Navbar from "./components/Navbar/Navbar.jsx";
import Experience from "./components/Experience/Experience.jsx";
import Education from "./components/Education/Education.jsx";
import Skills from "./components/Skills.jsx";
import Projects from "./components/Project/Projects.jsx";
import Certificates from "./components/certificates/Certificates.jsx";

function App() {

  return (
    <>
        {/* Navbar */}
        <Navbar/>

        {/*  Hero */}
        <Hero />

        {/* Experience */}
        <Experience/>

        {/* Education */}
        <Education />

        {/* Skills */}
        <Skills />

        {/* Projects */}
        <Projects/>

        {/* Certificates */}
        <Certificates/>

        {/* Footer */}
        <Footer/>
    </>
  )
}

export default App
