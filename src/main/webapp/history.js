function deleteHistory(element) {
    let id = element.parentNode.parentNode.children[0].textContent;
    window.location.href = "http://localhost:8080/history.jsp?id=" + id;
}

const contents = document.querySelectorAll("#content");

contents.forEach((element, idx) => {
    element.addEventListener('mouseover', () => {
        element.setAttribute("bgcolor", "#DCDCDC");
    });

    element.addEventListener('mouseout', () => {
        element.setAttribute("bgcolor", "#FFFFFF");
    });
});