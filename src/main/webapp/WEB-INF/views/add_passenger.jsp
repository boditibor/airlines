<html>
<head>
    <title>World Adventure Airlines</title>
</head>
<body>

<div class="container">
    <div class="title">Add a passenger</div>

    <%
    if (request.getAttribute("errors") != null) {
    %>
    <fieldset>
        <legend>Errors</legend>
        <ul>
            <% if (request.getAttribute("firstname error") != null) { %>
            <li class="error">first name error</li>
            <% } %>

            <% if (request.getAttribute("lastname error") != null) { %>
            <li class="error">first name error</li>
            <% } %>

            <% if (request.getAttribute("dob error") != null) { %>
            <li class="error">dob error</li>
            <% } %>
        </ul>
    </fieldset>
    <%
    }
    %>

    <fieldset>
        <legend>Passenger details</legend>
        <form action="AddPassenger" method="post">

            <div class="inputField">
                <label for="first-name" class="inputLabel">First name: </label>
                <input name="first-name" type="text" value="<%=request.getAttribute("first_name")%>"></input>
            </div>

            <div class="inputField">
                <label for="last-name" class="inputLabel">Last name: </label>
                <input name="last-name" type="text" value="<%=request.getAttribute("last_name") %>"></input>
            </div>

            <div class="inputField">
                <label for="dob" class="inputLabel">Date of birth: </label>
                <input name="dob" type="text" value="<%=request.getAttribute("dob") %>"></input>
            </div>

            <div class="inputField">
                <label for="first-name" class="inputLabel">Gender: </label>
                <select name="gender">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                </select>
            </div>

            <div class="inputField" id="submitField">
                <input id="submitBtn" type="submit" value="Add new passenger"></input>
            </div>

        </form>
    </fieldset>
</div>
</body>
</html>