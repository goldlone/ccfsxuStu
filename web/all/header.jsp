<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript" src="../assets/js/my/nav.js"></script>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/" style="cursor: default;">CCFSXU会员管理系统</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/logout">注销</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/master/selectMemberInfo">管理端</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/member/application">CSP预报名</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">CSP成绩分析</a></li>
      </ul>
      <!-- <form class="navbar-form navbar-right">
        <input type="text" class="form-control" placeholder="Search...">
      </form> -->
    </div>
  </div>
</nav>
