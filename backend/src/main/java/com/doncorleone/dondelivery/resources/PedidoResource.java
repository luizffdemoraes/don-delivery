package com.doncorleone.dondelivery.resources;

import com.doncorleone.dondelivery.domain.Pedido;
import com.doncorleone.dondelivery.dto.PedidoResponse;
import com.doncorleone.dondelivery.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<PedidoResponse> insert(@Valid @RequestBody Pedido obj) {
		PedidoResponse pedidoResponse = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(pedidoResponse);
	}

	@RequestMapping(value = "/paged", method=RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findAll(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="instante") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Pedido> list = service.findAllPaged(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="instante") String orderBy,
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Pedido> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}

	@PutMapping("/{id}/status/{idOrder}")
	public ResponseEntity<Pedido> setOrder(@PathVariable Integer id, @PathVariable Integer idOrder) {
		Pedido dto = service.setOrder(id, idOrder);
		return ResponseEntity.ok().body(dto);
	}

	@PutMapping("/{id}/pagamento/{idPayment}")
	public ResponseEntity<Pedido> setPayment(@PathVariable Integer id, @PathVariable Integer idPayment) {
		Pedido dto = service.setPayment(id, idPayment);
		return ResponseEntity.ok().body(dto);
	}
}