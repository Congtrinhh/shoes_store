<aside class="nav">
	<h2 class="logo px-4 py-2">Dashboard</h2>

	<ul class="parent-menu px-4">
		<li><a data-bs-toggle="collapse" href="#sub-menu-1" role="button"
			aria-expanded="false" aria-controls="collapseExample"> <span>manage</span>
				<span class="child-menu__trigger"><i
					class="fas fa-caret-right"></i></span>
		</a>
			<ul class="child-menu collapse collapse px-3" id="sub-menu-1">
				<li><a href="#">Admin</a></li>
				<li><a href="${pageContext.request.contextPath}/product-read">product</a></li>
				<li><a
					href="${pageContext.request.contextPath}/specific-product-read">specific
						product</a></li>
				<li><a href="${pageContext.request.contextPath}/user-read">User</a></li>
				<li><a href="${pageContext.request.contextPath}/">order</a></li>
			</ul></li>

		<li><a href="${pageContext.request.contextPath}/" class="">home
				page</a></li>
	</ul>
</aside>

<main class="content__top">
	<h1 class="page__header"></h1>

	<div id="nav__toggle" class="on">
		<i class="nav__toggle__close fas fa-bars"></i>
		<!-- <i class="nav__toggle__open fas fa-caret-right"></i> -->
	</div>

	<div class="admin-info">
		<a href="" class="image-parent"> <img src="" alt="">
		</a>
		<p class="admin-name">Congtrinhh</p>
		<p class="icon"></p>
	</div>
</main>