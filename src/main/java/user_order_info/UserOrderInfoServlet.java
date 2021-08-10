package user_order_info;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db_user_order_info_utils.OrderEntity;
import db_user_order_info_utils.ProductInOrderEntity;
import entities.District;
import entities.Order;
import entities.ProductInOrder;
import entities.Province;
import entities.User;
import entities.Ward;

@WebServlet(urlPatterns = {"/order-info", "/user-order-info"})
public class UserOrderInfoServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/* 
		 * lấy ra tất cả order dựa vào userId
		 * -> lấy được trạng thái đơn hàng, địa chỉ, mã đơn hàng
		 * -> từ mã đh, lấy ra tất cả item trong đh -> tính tổng tiền, tổng số lượng item
		 * -> lấy ra item đầu tiên trong list item của order -> lấy được số lượng đặt; id sp cụ thể->lấy được size, màu
		 * -> làm 3 bước trên với mọi tất cả order của người dùng đó
		 * -> ghép vào jsp
		 */
		try {
			HttpSession session = req.getSession();
			User logedInUser = (User) session.getAttribute(User.LOGED_IN_USER_IN_SESSION);
			
			if ( logedInUser!=null ) {
				
				int userId = logedInUser.getUser_id();
				Connection conn = common_utils.MyUtils.getStoredConnection(req);
				
				List<Order> orderTmpList = db_user_order_info_utils.UserOrderInfoDB.getOrderList(conn, userId);
				
				List<OrderEntity> orderList = new ArrayList<>();
				
				for (int i=0; i<orderTmpList.size(); i++) {
					int statusCode = orderTmpList.get(i).getOr_status();
					String specificAddress = orderTmpList.get(i).getSpecific_address();
					int wardId = orderTmpList.get(i).getWard_id();
					
					Ward ward = db_user_order_info_utils.UserOrderInfoDB.getWard(conn, wardId);
					String wardName = ward.getName();
					
					District district = db_user_order_info_utils.UserOrderInfoDB.getDistrict(conn, ward.getDistrict_id());
					String districtName = district.getName();
					
					Province province = db_user_order_info_utils.UserOrderInfoDB.getProvince(conn, ward.getProvince_id());
					String provinceName = province.getName();
					
					String completeAddress = specificAddress+", "+wardName+", "+districtName+", "+provinceName; // xong address
					String status = null;
					switch ( statusCode ) {																	 // xong status
					case 0: 
						status = "Đang xử lý";
						break;
					case 1:
						status = "Đang giao hàng";
						break;
					case 2:
						status = "Đã giao";
						break;
					case 3:
						status = "Đã hủy";
						break;
					default:
						status = "Đang xử lý";
					}
					
					List<ProductInOrder> productInOrderList = db_user_order_info_utils.UserOrderInfoDB.getProductInOrderList(conn, orderTmpList.get(i).getOrder_id());
					int itemCount = productInOrderList.size(); 											// xong itemCount
					
					// lấy ra số lượng, từ id-> lấy ra color, size, tên item
					ProductInOrder firstItemInOrder = productInOrderList.get(0);
					int speItemQuantity = firstItemInOrder.getPio_quantity(); 							// xong số lượng item nhỏ
					
					ProductInOrderEntity pioEntity = db_user_order_info_utils.UserOrderInfoDB.getProductInOrder(conn, firstItemInOrder.getSpecific_product_id());
					String base64Image = null;
					String speItemName = null;
					String speColorName =null;
					byte speSizeName = -1;
					
					if (  pioEntity!=null ) {
						base64Image = pioEntity.getBase64Image(); 								// xong ảnh item nhỏ
						speItemName = pioEntity.getName(); 								// xong tên item nhỏ
						speColorName = pioEntity.getColor_name(); 								// xong màu item nhỏ
						speSizeName = pioEntity.getSize_number(); 								// xong size item nhỏ
					}
					
					int totalPrice = 0;
					for (int j=0; j<productInOrderList.size(); j++) {
						//System.out.println("pio id: "+productInOrderList.get(j).getProduct_in_order_id());
						int price = db_user_order_info_utils.UserOrderInfoDB.getItemPrice(conn, productInOrderList.get(j).getProduct_in_order_id());
						//System.out.format("lan %d, price %d\n", j, price);
						totalPrice += price * productInOrderList.get(j).getPio_quantity(); 
					}
					
					String created_at = orderTmpList.get(i).getCreated_at();
					
					OrderEntity anOrder = new OrderEntity(new BigDecimal(totalPrice), completeAddress, status, itemCount, base64Image, speItemName, speItemQuantity, speColorName, speSizeName, created_at);
					orderList.add(anOrder);
				}
				
				// sắp xếp đơn hàng theoo ngày tạo, từ mới nhất đến cũ nhất
				Collections.sort(orderList, new Comparator<OrderEntity>() {
					@Override
					public int compare(OrderEntity o1, OrderEntity o2) {
						return o1.getCreated_at().compareTo(o2.getCreated_at());
					}
				});
				
				
				req.setAttribute("orderList", orderList);
				
				req.getSession().setAttribute( "userNavTabIndicator" , req.getServletContext().getContextPath() + "/order-info" );
			}
			
			
			
			
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/views/user_order_info/userOrderInfo.jsp");
			dispatcher.forward(req, resp);
		}
		catch (Exception e) {
			System.out.println("Lỗi user order-info: mes: "+e.getMessage());
		}
	}
}
