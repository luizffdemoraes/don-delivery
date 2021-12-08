package com.doncorleone.dondelivery.services;


import com.doncorleone.dondelivery.domain.ItemPedido;
import com.doncorleone.dondelivery.domain.Pedido;
import com.doncorleone.dondelivery.dto.PedidoResponse;
import com.doncorleone.dondelivery.entities.User;
import com.doncorleone.dondelivery.entities.enums.OrderStatus;
import com.doncorleone.dondelivery.entities.enums.PaymentStatus;
import com.doncorleone.dondelivery.repositories.ItemPedidoRepository;
import com.doncorleone.dondelivery.repositories.PedidoRepository;
import com.doncorleone.dondelivery.repositories.UserRepository;
import com.doncorleone.dondelivery.services.exceptions.AuthorizationException;
import com.doncorleone.dondelivery.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public PedidoResponse insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setUser(userRepository.findByEmail(obj.getUser().getEmail()));
		obj.setStatus(OrderStatus.PENDING);
		obj.setPaymentStatus(PaymentStatus.PENDING);
		obj = repo.save(obj);

		for (ItemPedido ip : obj.getItens()) {
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());

		return PedidoResponse.builder()
				.id(obj.getId())
				.descricao(obj.getDescricao())
				.email(obj.getUser().getEmail())
				.endereco(obj.getEndereco())
				.instante(obj.getInstante())
				.itens(obj.getItens())
				.latitude(obj.getLatitude())
				.longitude(obj.getLongitude())
				.paymentStatus(obj.getPaymentStatus())
				.status(obj.getStatus())
				.build();
	}

	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		User user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		User cliente =  userService.find(user.getId());
		return repo.findByUser(cliente, pageRequest);
	}

	@Transactional(readOnly = true)
	public Page<Pedido> findAllPaged(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Pedido> list = repo.findAll(pageRequest);
		return list;
	}

	@Transactional
	public Pedido setDelivered(Integer id) {
		Pedido order = repo.getById(id);
		order.setStatus(OrderStatus.DELIVRED);
		order = repo.save(order);
		return order;
	}

	@Transactional
	public Pedido setRoute(Integer id) {
		Pedido order = repo.getById(id);
		order.setStatus(OrderStatus.EN_ROUTE);
		order = repo.save(order);
		return order;
	}

	@Transactional
	public Pedido setCanceled(Integer id) {
		Pedido order = repo.getById(id);
		order.setStatus(OrderStatus.CANCELED);
		order.setPaymentStatus(PaymentStatus.CANCELED);
		order = repo.save(order);
		return order;
	}

	@Transactional
	public Pedido setPayment(Integer id, Integer idPayment) {
		Pedido order = repo.getById(id);
		order.setPaymentStatus(PaymentStatus.toEnum(idPayment));
		order = repo.save(order);
		return order;
	}

	public Pedido setOrder(Integer id, Integer idOrder) {
		Pedido order = repo.getById(id);
		order.setStatus(OrderStatus.toEnum(idOrder));
		order = repo.save(order);
		return order;
	}

}