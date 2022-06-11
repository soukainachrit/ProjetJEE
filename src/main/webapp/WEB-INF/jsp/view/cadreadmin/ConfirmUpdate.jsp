<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 23/05/2022
  Time: 01:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<jsp:include page="../fragments/userheader.jsp" />

<div class="container">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">

            <jsp:include page="../fragments/usermenu.jsp" />

        </div>
    </nav>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm Update</title>
</head>
<body>
<div class="card shadow mb-4">
    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
        <h5 class="m-0 font-weight-bold text-primary">Do you really want to update the excisting Grades ?</h5>
    </div>
<div class="card-body">
    <f:form method="POST" action="/cadreadmin/Confirm" modelAttribute="Data" enctype = "multipart/form-data">
        <div class="form-row">
            <div class="form-group col-md-6">

<%--                <f:input type="hidden" value = "${Data.list}" path="list"  />
                <f:input type="hidden" value = "${Data.modelAtt}"  path="modelAtt" />--%>
                <input type = "submit" class="btn btn-primary text-white" value = "upload"  />
                <a class="btn btn-primary text-white" href="/cadreadmin/ImportNote"  >Annuler</a>
            </div>
        </div>
    </f:form>
</div>
</div>


</body>
</html>
