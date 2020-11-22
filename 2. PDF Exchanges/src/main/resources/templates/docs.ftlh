<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://fonts.googleapis.com/css2?family=Yeseva+One&display=swap" rel="stylesheet">
    <title>Document</title>
    <style>
        html {
            font-family: 'Yeseva One', cursive;
        }
        * {
            padding: 0;
            margin: 0;
        }
        header {
            padding: 20px;
            text-align: center;
            background-color: black;
            color: gold;
        }

        .main {
            width: 80vw;
            height: calc(100vh - 45px);
            margin: 0 auto;
            background-color: #cbcaca;
        }

        .switch {
            position: relative;
            display: inline-block;
            width: 60px;
            height: 34px;
        }

        /* Hide default HTML checkbox */
        .switch input {
            opacity: 0;
            width: 0;
            height: 0;
        }

        /* The slider */
        .slider {
            position: absolute;
            cursor: pointer;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: green;
            -webkit-transition: .4s;
            transition: .4s;
        }

        .slider:before {
            position: absolute;
            content: "";
            height: 26px;
            width: 26px;
            left: 4px;
            bottom: 4px;
            background-color: white;
            -webkit-transition: .4s;
            transition: .4s;
        }

        input:checked + .slider {
            background-color: blue;
        }

        input:focus + .slider {
            box-shadow: 0 0 1px blue;
        }

        input:checked + .slider:before {
            -webkit-transform: translateX(26px);
            -ms-transform: translateX(26px);
            transform: translateX(26px);
        }


    </style>
</head>
<body>
    <header style="letter-spacing: 3px">
        <h1>Документъ</h1>
        <label class="switch">
            <input type="checkbox" id="lang" value="tat">
            <span class="slider"></span>
        </label>
    </header>
<div class="main">
    <div>
        <form method="post">
            <div>
                <input type="text" placeholder="name" name="name">
            </div>
            <div>
                <input type="text" placeholder="surname" name="surname">
            </div>
            <div>
                <input type="text" placeholder="patronymic" name="patronymic">
            </div>
            <div>
                <input type="text" placeholder="birthday" name="birthday">
            </div>
            <div>
                <input type="text" placeholder="passportId" name="passportId">
            </div>
            <input type="hidden" id="lang-input" value="tat" name="language">
            <input type="submit" value="Машина">
        </form>
        <button onclick="send()">
            Оправить
        </button>
    </div>
    <script>

        document.getElementById('lang').addEventListener('change', (e) => {
            document.querySelector("#lang").value = e.target.checked ? 'ru' : 'tat';
            document.querySelector("#lang-input").value = e.target.checked ? 'ru' : 'tat';
        })
    </script>
</div>
</body>
</html>