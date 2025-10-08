const pageValue = "1";

const url = `http://localhost:8080/Lab1_war_exploded/helloServlet?page=${pageValue}`;

fetch(url)
  .then(response => {
    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return response.text();
  })
  .then(data => {
    console.log("Response from servlet:", data);
  })
  .catch(error => {
    console.error("Error:", error);
  });
