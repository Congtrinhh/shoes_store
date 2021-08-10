<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-12 col-md-4 col-lg-3 offset-lg-1  user-nav-container">
                <nav class="user-nav">
                    <!-- Badge -->
                    <div class="nav-badge">
                        <div class="img-parent"><img src="<c:if test="${sessionScope.login_token=='user'}">${pageContext.request.contextPath}/resources/imgs/avatar${sessionScope.logedInUser.u_avatar}</c:if>"
                         alt=""></div>
                        <h2><c:if test="${sessionScope.login_token=='user'}">${sessionScope.logedInUser.u_login_name}</c:if></h2>
                        <!-- button to show, hide nav -->
                        <button class="btn" type="button" data-bs-toggle="collapse" data-bs-target="#user-navigation-bar"
                            aria-expanded="false" aria-controls="user-navigation-bar"><i class="fas fa-ellipsis-v"></i></button>
                    </div>
                    <!-- Nav -->
                    <ul class="collapse show" id="user-navigation-bar" tab-indicator="${sessionScope.userNavTabIndicator}">
                        <li>
                            <a href="/info">
                                <i class="fas fa-home"></i>
                                <span>Thông tin cá nhân</span>
                            </a>
                        </li>
                        <li>
                            <a href="/order-info">
                                <i class="fas fa-inbox"></i>
                                <span>Đơn hàng</span>
                            </a>
                        </li>
                        <li>
                            <a href="/logout">
                                <i class="fas fa-sign-out-alt"></i>
                                <span>Thoát</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>