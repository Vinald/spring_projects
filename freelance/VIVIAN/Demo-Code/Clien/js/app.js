// Client-side Buyambi Care demo
const $ = (id) => document.getElementById(id);

const state = {
    pain: null,
    stand: null,
    hospital: null,
    location: null,
    severity: "mild",
};

// translations (small set)
const T = {
    en: { sos: "SOS", help: "HELP", continue: "Continue", nearest: "Nearest" },
    lg: { sos: "NYAMBA", help: "OBUYAMBI", continue: "Okutambula", nearest: "W’ayegatta" },
    sw: { sos: "SAIDIA", help: "SAADA", continue: "Endelea", nearest: "Karibu" },
};

function t(key) {
    const lang = $("lang")?.value || "en";
    return (T[lang] && T[lang][key]) || T["en"][key] || key;
}

function setLocationText(txt) {
    $("loc").textContent = txt;
}

// ask browser for location
if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
        (pos) => {
            const { latitude, longitude } = pos.coords;
            state.location = { latitude, longitude };
            setLocationText(latitude.toFixed(5) + ", " + longitude.toFixed(5));
        },
        (err) => {
            // fallback
            setLocationText("Kampala (approx)");
        },
        { maximumAge: 60000, timeout: 5000 }
    );
} else {
    setLocationText("Unavailable");
}

// UI helpers
function show(id) {
    document.querySelectorAll(".screen").forEach((s) => s.classList.add("hidden"));
    document.querySelectorAll(".card.modal").forEach((m) => m.classList.add("hidden"));
    $(id).classList.remove("hidden");
}

// segment buttons
function hookSeg(id, stateKey) {
    const el = $(id);
    if (!el) return;
    el.querySelectorAll(".seg-btn").forEach((btn) => {
        btn.addEventListener("click", () => {
            el.querySelectorAll(".seg-btn").forEach((b) => b.classList.remove("active"));
            btn.classList.add("active");
            state[stateKey] = btn.dataset.val;
            computeSeverity();
        });
    });
}

function computeSeverity() {
    // severe if pain severe or cannot stand
    if (state.pain === "severe" || state.stand === "no") state.severity = "severe";
    else if (state.pain === "moderate" || state.stand === "help") state.severity = "moderate";
    else state.severity = "mild";
    $("sev-label") && ($("sev-label").textContent = capitalize(state.severity));
}

function capitalize(s) {
    return s ? s.charAt(0).toUpperCase() + s.slice(1) : s;
}

// navigation
$("nav-help").addEventListener("click", () => {
    show("screen-help");
    setActiveNav("nav-help");
});
$("nav-vehicles").addEventListener("click", () => {
    show("screen-vehicles");
    setActiveNav("nav-vehicles");
    renderVehicles();
});
$("nav-track").addEventListener("click", () => {
    show("screen-tracking");
    setActiveNav("nav-track");
});

function setActiveNav(id) {
    document.querySelectorAll(".nav-item").forEach((n) => n.classList.remove("active"));
    $(id).classList.add("active");
}

// main buttons
$("btn-clear").addEventListener("click", () => {
    ["pain", "stand", "hospital"].forEach((k) => (state[k] = null));
    document.querySelectorAll(".seg-btn").forEach((b) => b.classList.remove("active"));
    computeSeverity();
});

$("btn-continue").addEventListener("click", () => {
    // go to vehicles
    renderVehicles();
    show("screen-vehicles");
});

$("btn-sos").addEventListener("click", () => {
    // set severe, cannot stand, nearest hospital
    state.pain = "severe";
    state.stand = "no";
    state.hospital = "nearest";
    computeSeverity();
    renderVehicles();
    show("screen-vehicles");
});

$("btn-help").addEventListener("click", () => {
    show("screen-help");
});
$("btn-back").addEventListener("click", () => {
    show("screen-help");
});

// vehicles data
const vehicles = [
    { id: "boda", title: "Emergency Boda", eta: 3, dist: 1.4, cost: 2000, type: "boda" },
    { id: "car", title: "Car", eta: 6, dist: 3.2, cost: 6000, type: "car" },
    { id: "ambulance", title: "Ambulance", eta: 8, dist: 4.1, cost: 100000, type: "ambulance" },
    { id: "clinic", title: "Mobile Clinic", eta: 12, dist: 6.2, cost: 150000, type: "clinic" },
];

function renderVehicles() {
    const list = $("vehicles");
    list.innerHTML = "";
    vehicles.forEach((v) => {
        // if severe & cannot stand -> disable boda
        const disabled = state.severity === "severe" && v.type === "boda";
        const card = document.createElement("div");
        card.className = "vehicle-card";
        card.innerHTML = `
      <div class="vehicle-info">
        <div class="avatar">${v.title
            .split(" ")
            .map((w) => w[0])
            .join("")
            .slice(0, 2)}</div>
        <div>
          <div style="font-weight:700">${v.title}</div>
          <div class="small muted">ETA ${v.eta} min • ${v.dist} km</div>
        </div>
      </div>
      <div style="display:flex;flex-direction:column;align-items:flex-end;gap:8px">
        <div class="pill">UGX ${Number(v.cost).toLocaleString()}</div>
        <button class="big select-vehicle" data-id="${v.id}" ${disabled ? "disabled" : ""}>${
            disabled ? "Unavailable" : "Select"
        }</button>
      </div>
    `;
        if (disabled) card.style.opacity = 0.45;
        list.appendChild(card);
    });

    // hook selects
    document.querySelectorAll(".select-vehicle").forEach((btn) => {
        btn.addEventListener("click", () => {
            const id = btn.dataset.id;
            startTrackingFor(id);
            show("screen-tracking");
            setActiveNav("nav-track");
        });
    });
}

function startTrackingFor(vehicleId) {
    // populate tracking mock
    const v = vehicles.find((x) => x.id === vehicleId);
    $("rider-name").textContent = v.title.replace("Emergency ", "");
    $("rider-rating").textContent = "★ " + (4 + Math.random().toFixed(1));
    $("status-pill").textContent = "On the way — ETA " + v.eta + " min";
    // set severity color
    const el = $("rider-photo");
    el.classList.remove("status-green", "status-blue", "status-red");
    if (state.severity === "mild") el.classList.add("status-green");
    else if (state.severity === "moderate") el.classList.add("status-blue");
    else el.classList.add("status-red");
}

// small helpers
function renderTranslations() {
    // update SOS and HELP to currently selected language
    document.querySelectorAll(".circle.primary").forEach((b) => {
        b.textContent = t("help");
    });
    document.querySelectorAll(".circle.danger").forEach((b) => {
        b.textContent = t("sos");
    });
    document.querySelectorAll(".big").forEach((b) => {
        if (b.id === "btn-continue") b.textContent = t("continue");
    });
}

$("lang").addEventListener("change", () => {
    renderTranslations();
});
renderTranslations();

hookSeg("pain", "pain");
hookSeg("stand", "stand");
hookSeg("hospital", "hospital");
computeSeverity();

// basic call/message handlers
$("btn-call-responder").addEventListener("click", () => alert("Calling responder (simulated)"));
$("btn-message").addEventListener("click", () => alert("Open message (simulated)"));

// nav quick actions
$("nav-vehicles").addEventListener("click", () => {
    renderVehicles();
});
