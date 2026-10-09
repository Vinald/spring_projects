/*
    Buyambi Care — Rider App Demo
    - Single-file prototype. No backend.
    - Simulated flows with localStorage for state.
*/

// Simple app state
const state = {
    phone: null,
    name: "Buyambi Rider",
    online: false,
    incoming: 0,
    currentJob: null,
    language: localStorage.getItem("lang") || "en",
    wallet: parseInt(localStorage.getItem("wallet") || "0", 10),
    earningsToday: parseInt(localStorage.getItem("earningsToday") || "0", 10),
    transactions: JSON.parse(localStorage.getItem("transactions") || "[]"),
    verified: localStorage.getItem("verified") === "true",
};

// Utility
const $ = (id) => document.getElementById(id);
const show = (id) => {
    document.querySelectorAll(".screen").forEach((s) => s.classList.add("hidden"));
    document.querySelectorAll(".card.modal").forEach((m) => m.classList.add("hidden"));
    if ($(id)) $(id).classList.remove("hidden");
};
const updateHeader = () => {
    $("header-right").textContent = state.online ? "Online" : "Offline";
    $("status-pill").textContent = state.online ? "Online" : "Offline";
    $("incoming-count").textContent = state.incoming;
    $("today-earn").textContent = "UGX " + state.earningsToday.toLocaleString();
    $("wallet-balance").textContent = "UGX " + state.wallet.toLocaleString();
    $("verification-status").textContent = state.verified ? "Verified" : "Not Verified";
    document.querySelectorAll(".nav-item").forEach((n) => n.classList.remove("active"));
};
const persist = () => {
    localStorage.setItem("wallet", String(state.wallet));
    localStorage.setItem("earningsToday", String(state.earningsToday));
    localStorage.setItem("transactions", JSON.stringify(state.transactions));
    localStorage.setItem("verified", String(state.verified));
};

// Navigation handlers
$("btn-send-otp").addEventListener("click", () => {
    const phone = $("login-phone").value.trim() || "+256700000000";
    state.phone = phone;
    $("otp-phone").textContent = phone;
    show("view-otp");
});

$("btn-verify-otp").addEventListener("click", () => {
    const otp = $("otp-code").value.trim();
    // in demo OTP is 1234
    if (otp === "1234") {
        // go to dashboard
        $("rider-name").textContent = state.name;
        show("view-dashboard");
        updateHeader();
    } else {
        alert("Incorrect OTP. Use 1234 in this demo.");
    }
});

$("demo-guest").addEventListener("click", (e) => {
    e.preventDefault();
    show("view-dashboard");
    updateHeader();
});

// ONLINE toggle
const toggle = $("toggle-online");
toggle.addEventListener("click", () => {
    state.online = !state.online;
    toggle.classList.toggle("on", state.online);
    updateHeader();
});

// Bottom nav
$("nav-dashboard").addEventListener("click", () => {
    show("view-dashboard");
    $("nav-dashboard").classList.add("active");
});
$("nav-history").addEventListener("click", () => {
    show("view-history");
    $("nav-history").classList.add("active");
    /* history view not implemented separately, use wallet as history */ show("view-wallet");
    $("nav-history").classList.add("active");
});
$("nav-wallet2").addEventListener("click", () => {
    show("view-wallet");
    $("nav-wallet2").classList.add("active");
});
$("nav-profile2").addEventListener("click", () => {
    show("view-profile");
    $("nav-profile2").classList.add("active");
});

// open wallet from dashboard
$("open-wallet").addEventListener("click", () => {
    show("view-wallet");
});

// simulate incoming job
$("simulate-request").addEventListener("click", () => {
    if (!state.online) {
        alert("You must be online to receive jobs. Toggle the Online switch.");
        return;
    }
    // increment incoming and show modal
    state.incoming += 1;
    updateHeader();
    const incomingModal = $("incoming-modal");
    incomingModal.classList.remove("hidden");
    // play beep until accepted or declined
    const beep = $("beep");
    beep.loop = true;
    beep.play().catch(() => {
        /* audio may be blocked by browser until interaction */
    });
    // save current job details
    state.currentJob = {
        id: "JOB-" + Date.now(),
        client: "Sarah",
        pickup: "Kitante",
        drop: "Kawempe",
        distance_km: 2.1,
        eta: 5,
        notes: "Patient has fever",
    };
});

// handle accept/decline
$("btn-decline").addEventListener("click", () => {
    $("incoming-modal").classList.add("hidden");
    state.incoming = Math.max(0, state.incoming - 1);
    updateHeader();
    $("beep").pause();
    state.currentJob = null;
});

$("btn-accept").addEventListener("click", () => {
    $("incoming-modal").classList.add("hidden");
    $("beep").pause();
    // start active trip view
    $("trip-client-name").textContent = "Client: " + state.currentJob.client;
    $("pickup-eta").textContent = state.currentJob.eta + " min";
    $("trip-status").textContent = "Heading to pickup";
    show("view-active");
    // set job status
    state.jobPhase = "heading";
    // decrement incoming
    state.incoming = Math.max(0, state.incoming - 1);
    updateHeader();
});

// active trip controls
$("btn-arrived").addEventListener("click", () => {
    state.jobPhase = "arrived";
    $("trip-status").textContent = "Arrived at pickup";
    alert("Mark as arrived. Now press Start Trip when passenger onboard.");
});
$("btn-start-trip").addEventListener("click", () => {
    state.jobPhase = "ontrip";
    $("trip-status").textContent = "On trip to dropoff";
    alert("Trip started. Use Complete Trip when finished.");
});
$("btn-end-trip").addEventListener("click", () => {
    // finish trip: show completion view
    // compute fare (mock)
    const fare = 3500;
    $("fare-total").textContent = "UGX " + fare.toLocaleString();
    show("view-complete");
});

// cancel trip
$("btn-cancel-trip").addEventListener("click", () => {
    if (confirm("Cancel this trip?")) {
        state.currentJob = null;
        show("view-dashboard");
    }
});

// call client (simulated)
$("btn-call").addEventListener("click", () => {
    alert("Calling client: " + state.currentJob.client + " (simulated)");
});

// rating stars
document.querySelectorAll(".star").forEach((btn) => {
    btn.addEventListener("click", (e) => {
        const val = e.target.getAttribute("data-star");
        document.querySelectorAll(".star").forEach((s) => (s.style.opacity = 0.4));
        for (let i = 0; i < val; i++) {
            document.querySelectorAll(".star")[i].style.opacity = 1;
        }
        // store rating value
        $("btn-submit-rating").dataset.rating = val;
    });
});

// submit rating and add to wallet
$("btn-submit-rating").addEventListener("click", () => {
    const rating = $("btn-submit-rating").dataset.rating || "5";
    const comment = $("rating-comment").value || "";
    const fare = 3500;
    // update wallet and earnings
    state.wallet += fare;
    state.earningsToday += fare;
    state.transactions.unshift({
        id: "TX-" + Date.now(),
        amount: fare,
        type: "trip",
        note: "Fare paid",
        date: new Date().toLocaleString(),
    });
    persist();
    updateHeader();
    alert("Rating submitted: " + rating + " stars. Fare added to wallet.");
    // go back to dashboard
    state.currentJob = null;
    show("view-dashboard");
});

// wallet behaviors
$("btn-add-funds").addEventListener("click", () => {
    const amt = parseInt(prompt("Amount to add (UGX)", "10000"), 10);
    if (amt && !isNaN(amt)) {
        state.wallet += amt;
        state.transactions.unshift({
            id: "TX-" + Date.now(),
            amount: amt,
            type: "topup",
            note: "Top-up",
            date: new Date().toLocaleString(),
        });
        persist();
        updateHeader();
        alert("Added UGX " + amt.toLocaleString());
    }
});

$("btn-withdraw").addEventListener("click", () => {
    const amt = parseInt($("withdraw-amount").value || "0", 10);
    if (!amt || isNaN(amt)) {
        alert("Enter a valid amount");
        return;
    }
    if (amt > state.wallet) {
        alert("Insufficient balance");
        return;
    }
    // simulate withdraw
    state.wallet -= amt;
    state.transactions.unshift({
        id: "TX-" + Date.now(),
        amount: -amt,
        type: "withdraw",
        note: "Withdraw to Momo",
        date: new Date().toLocaleString(),
    });
    persist();
    updateHeader();
    alert(
        "Withdraw initiated (simulated). UGX " +
            amt.toLocaleString() +
            " will be sent to your Momo."
    );
});

// show transactions list when wallet opened
function renderTransactions() {
    const list = $("transactions-list");
    list.innerHTML = "";
    if (state.transactions.length === 0) {
        list.innerHTML = '<div class="small-muted">No transactions yet</div>';
        return;
    }
    state.transactions.forEach((tx) => {
        const div = document.createElement("div");
        div.className = "history-item";
        div.innerHTML =
            "<div>" +
            (tx.note || tx.type) +
            '</div><div class="small-muted">UGX ' +
            tx.amount.toLocaleString() +
            "</div>";
        list.appendChild(div);
    });
}
$("open-wallet").addEventListener("click", renderTransactions);
$("nav-wallet2").addEventListener("click", renderTransactions);

// profile actions
$("btn-upload-docs").addEventListener("click", () => {
    if (confirm("Simulate upload and verification?")) {
        state.verified = true;
        persist();
        updateHeader();
        alert("Documents uploaded. Verification in progress (simulated). Status: Verified.");
    }
});
$("btn-logout").addEventListener("click", () => {
    if (confirm("Log out?")) {
        // reset minimal state
        // For demo, just reload to clear
        location.reload();
    }
});

// initial UI
show("view-login");
updateHeader();

// Language / translations
const TRANSLATIONS = {
    help: { en: "HELP", lg: "NYAMBA", sw: "SAIDIA" },
    ai_support: { en: "AI Support", lg: "AI Obuyambi", sw: "Msaada AI" },
};

function applyLanguage() {
    // set help button text
    const helpBtn = $("help-button");
    if (helpBtn) helpBtn.textContent = TRANSLATIONS.help[state.language] || TRANSLATIONS.help.en;
    // ai support label
    const aiSupport = document.querySelectorAll("#open-ai");
    aiSupport.forEach((el) => {
        el.textContent = TRANSLATIONS.ai_support[state.language] || TRANSLATIONS.ai_support.en;
    });
    // persist
    localStorage.setItem("lang", state.language);
}

// hook language selector if present
const langSelect = $("lang-select");
if (langSelect) {
    langSelect.value = state.language;
    langSelect.addEventListener("change", (e) => {
        state.language = e.target.value;
        applyLanguage();
    });
}
applyLanguage();
