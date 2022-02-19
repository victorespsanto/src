package br.com.albacares.bluefood.application.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.albacares.bluefood.domain.pedido.Carrinho;
import br.com.albacares.bluefood.domain.pedido.ItemPedido;
import br.com.albacares.bluefood.domain.pedido.ItemPedidoPK;
import br.com.albacares.bluefood.domain.pedido.ItemPedidoRepository;
import br.com.albacares.bluefood.domain.pedido.Pedido;
import br.com.albacares.bluefood.domain.pedido.PedidoRepository;
import br.com.albacares.bluefood.domain.pedido.Pedido.Status;
import br.com.albacares.bluefood.util.SecurityUtils;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemmPedidoRepository;
	
	public Pedido criarEPagar(Carrinho carrinho, String numCartao) {
		
		Pedido pedido = new Pedido();
		pedido.setData(LocalDateTime.now());
		pedido.setCliente(SecurityUtils.loggedCliente());
		pedido.setRestaurante(carrinho.getRestaurante());
		pedido.setStatus(Status.Producao);
		pedido.setTaxaEntrega(carrinho.getRestaurante().getTaxaEntrega());
		pedido.setSubtotal(carrinho.getPrecoTotal(false));
		pedido.setTotal(carrinho.getPrecoTotal(true));
		
		pedido = pedidoRepository.save(pedido);
		
		int ordem = 1;
		
		for (ItemPedido itemPedido : carrinho.getItens()) {
			itemPedido.setId(new ItemPedidoPK(pedido, ordem++));
			itemmPedidoRepository.save(itemPedido);
		}
		
		// TODO: Pagamento
		
		return pedido;
		
		
	}
	
}
