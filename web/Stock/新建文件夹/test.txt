<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"> 
	<title>Bootstrap 实例 - 下拉菜单（Dropdowns）</title>
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="dropdown">
	<button type="button" class="btn dropdown-toggle" id="dropdownMenu1" 
			data-toggle="dropdown">
		主题
		<span class="caret"></span>
	</button>
	<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
		<li role="presentation">
			<a role="menuitem" tabindex="-1" href="#">Java</a>
		</li>
		<li role="presentation">
			<a role="menuitem" tabindex="-1" href="#">数据挖掘</a>
		</li>
		<li role="presentation">
			<a role="menuitem" tabindex="-1" href="#">数据通信/网络</a>
		</li>
		<li role="presentation" class="divider"></li>
		<li role="presentation">
			<a role="menuitem" tabindex="-1" href="#">分离的链接</a>
		</li>
	</ul>
</div>

</body>
</html>