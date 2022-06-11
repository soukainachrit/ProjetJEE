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
    <title>Title</title>

</head>
<body>

<div class="card shadow mb-4">
  <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
      <h5 class="m-0 font-weight-bold text-primary">Choisir un niveau et un modéle</h5>
  </div>
<div class="card-body">
    <form method="POST" action="/cadreadmin/uploadFile" modelAttribute="Import_Attribute" enctype = "multipart/form-data">
        <div class="form-row mt-3">
            <div class="form-group col-md-6 p-2 m-3">
                <label >Niveau</label>
                <select name="Module" class="form-control" path="module.idModule">
                    <option value="">Choisir le module</option>
                    <c:forEach items="${ModuleList}" var="module">
                        <option value="${module.idModule}">${module.titre}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-md-6 p-2 m-3">
                <label>Session</label>
                <select name="Session"  class="form-control" path="session">
                    <option value=""> Choisir une session </option><option value="1">Session Normale </option><option value="2">Session Rattrapage</option>                               </select>
            </div>
        </div>



        <div class="mt-3" >
            <div class="p-2 m-3">
                <h5>Selectionner un fichier à importer :</h5>
        <input id="file"  type = "file" name = "file" class="btn btn-secondary text-white" value = "Browse File" onchange="return fileValidation()"/> <br /> <br />
                <h5>
            <small id="sfile" style="color:#f20b0b" class="border border-danger p-2 mb-2 bg-danger text-white" hidden >Ce champs doit etre rempli</small></h5>
            </div>
            <div id="upload" class="p-2 m-3" hidden>
                <h5>Importer les données :</h5>
        <input class="btn btn-secondary text-white" type = "submit" value = "upload" /> <br /> <br />
        </div>
        </div>


    </form>
</div>
</div>


</body>
<script>
        function fileValidation() {
            var fileInput =
                document.getElementById('file');

            var filePath = fileInput.value;

            console.log(filePath);
            // Allowing file type
            var allowedExtensions = ".xlsx";

            if (!filePath.includes(allowedExtensions)) {
                document.getElementById('sfile').removeAttribute("hidden");
                document.getElementById('sfile').innerHTML = 'Please choose an excel file.';
                document.getElementById('upload').hidden=true;

                fileInput.value = '';
                return false;
            }else {
                document.getElementById('sfile').hidden=true;
                document.getElementById('upload').removeAttribute("hidden");

            }
            if (filePath==="") {
                document.getElementById('sfile').removeAttribute("hidden");
                document.getElementById('sfile').innerHTML = 'Please Choose a file';
                return false;
            }else {
                document.getElementById('sfile').hidden=true;
                document.getElementById('upload').removeAttribute("hidden");

            }

        }


</script>
</html>
