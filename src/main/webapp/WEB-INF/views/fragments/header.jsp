<header class="header">
	<div class="header__top container">
		<div class="logo-and-search">
			<h1 class="logo">run youth</h1>
			<div class="header__form-control">
				<input type="text" name="home-search" placeholder="Search">
				<!--search for information through the website: products, articles, ..-->
				<button type="button" class="btn">
					<i class="fas fa-search"></i>
				</button>
			</div>
		</div>
		<div class="nav-and-cart">
			<nav class="header__nav">
				<ul>
					<c:forEach items="${categoryList}" var="cate">
						<li><a
							href="${pageContext.request.contextPath}/${cate.c_slug}"
							class="active-link">${cate.c_name}</a></li>
					</c:forEach>
				</ul>
			</nav>
			<div class="header__cart">
				<i class="fas fa-shopping-cart"></i>
				<p>cart</p>
				<span class="number-indicator">1</span>
			</div>
		</div>
	</div>
	<div class="header__ribbon">
		<div class="header__ribbon__message">Our biggest sale yet 50%
			off all summer shoes</div>
	</div>
</header>