<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/webstock/webfiles/images/logo.png">

    <title>Chat</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="../../../templates/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="../../../templates/css/style.css">
</head>
<body>

<div class="container">
    <div class="row">
        <form method="post">

                                    <tr>
                                        <#list messages as message>
                                    <tr>
                                        <td>${message.content}</td>

                                    </tr>
                                    </#list>
                                    </tr>

        </form>
    </div>
</div>

</body>
</html>