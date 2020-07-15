package com.example.maptechnology.manutencaoapp.rest;

import com.example.maptechnology.manutencaoapp.models.AllUsers;
import com.example.maptechnology.manutencaoapp.models.Areas;
import com.example.maptechnology.manutencaoapp.models.Atividade;
import com.example.maptechnology.manutencaoapp.models.CalendarioDetalhe;
import com.example.maptechnology.manutencaoapp.models.Atividades;
import com.example.maptechnology.manutencaoapp.models.CincoPorques;
import com.example.maptechnology.manutencaoapp.models.Conjuntos;
import com.example.maptechnology.manutencaoapp.models.Estoque;
import com.example.maptechnology.manutencaoapp.models.Falhas;
import com.example.maptechnology.manutencaoapp.models.IdArea;
import com.example.maptechnology.manutencaoapp.models.IdOrdem;
import com.example.maptechnology.manutencaoapp.models.OrdemManutencao;
import com.example.maptechnology.manutencaoapp.models.Peca;
import com.example.maptechnology.manutencaoapp.models.Pecas;
import com.example.maptechnology.manutencaoapp.models.Porques;
import com.example.maptechnology.manutencaoapp.models.QrCodeResult;
import com.example.maptechnology.manutencaoapp.models.SubConjunto;
import com.example.maptechnology.manutencaoapp.models.Manutencoes;
import com.example.maptechnology.manutencaoapp.models.OrdensDoDia;
import com.example.maptechnology.manutencaoapp.models.SubConjuntos;
import com.example.maptechnology.manutencaoapp.models.Usuario;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by MAPTECHNOLOGY on 22/02/2019.
 */

public interface RetrofitClass {

    @FormUrlEncoded
    @POST("usuario/criar/")
    Call<Usuario> cadastraUsuario(@Field("nome") String nome ,
                               @Field("dataCriacao") String dataCriacao,
                               @Field("matricula") String matricula,
                               @Field("senha") String senha,
                               @Field("hierarquia") String hierarquia,
                               @Field("status") String status,
                               @Field("valorhh") String valorhh

    );

    @FormUrlEncoded
    @POST("usuario/login/")
    Call<Usuario> logar(@Field("matricula") String matricula ,
                                  @Field("senha") String senha
    );



    @FormUrlEncoded
    @POST("atividadeOrdem/detalheCalendarioPorData/")
    Call<Atividades> detalheCalendarioPorData(@Field("dia") String dia);

    @FormUrlEncoded
    @POST("atividadeOrdem/porOrdem/")
    Call<Atividades> atividadesPorOrdem(@Field("idOrdem") int idOrdem);

    @FormUrlEncoded
    @POST("atividadeOrdem/alterar/")
    Call<Atividades> alteraStatusAtividade(@Field("id") int id,
                                           @Field("status") int status,
                                           @Field("dataInicio") String dataInicio,
                                           @Field("dataInicioPausa") String dataInicioPausa,
                                           @Field("dataFimPausa") String dataFimPausa,
                                           @Field("dataFim") String dataFim);

    @FormUrlEncoded
    @POST("atividadeOrdem/alterar/")
    Call<Void> insereObservacaoNaAtividade(@Field("id") int id,
                                           @Field("observacao") String observacao);

    @FormUrlEncoded
    @POST("atividadeOrdem/criar/")
    Call<Atividade> criarAtividadeAgendada(
                                         @Field("dataManutencao") String dataManutencao,
                                         @Field("manutencao") int manutencao,
                                         @Field("responsavel") int responsavel,
                                         @Field("idOrdem") int idOrdem,
                                         @Field("idPeca") String idPeca,
                                         @Field("idConjunto") String idConjunto,
                                         @Field("idSubConjunto") String idSubConjunto,
                                         @Field("descricao") String descricao,
                                         @Field("manutencaoCorretiva") String manutencaoCorretiva
                                   );

    @FormUrlEncoded
    @POST("atividadeOrdem/criar/")
    Call<Atividade> criarAtividadeRealizada(
            @Field("dataManutencao") String dataManutencao,
            @Field("manutencao") int manutencao,
            @Field("responsavel") int responsavel,
            @Field("idOrdem") int idOrdem,
            @Field("idPeca") String idPeca,
            @Field("idConjunto") String idConjunto,
            @Field("idSubConjunto") String idSubConjunto,
            @Field("dataInicio") String dataInicio,
            @Field("dataFim") String dataFim,
            @Field("status") int status,
            @Field("descricao") String descricao,
            @Field("manutencaoCorretiva") String manutencaoCorretiva



    );

    @FormUrlEncoded
    @POST("ordem/criar/")
    Call<IdOrdem> criarOrdem(@Field("descricao") String descricao,
                             @Field("codigo") String codigo,
                             @Field("dataCriacao") String dataCriacao,
                             @Field("frequenciaManutencao") int frequenciaManutencao,
                             @Field("responsavelCriacao") int responsavelCriacao,
                             @Field("tipo") int tipo,
                             @Field("status")int status,
                             @Field("falha") String falha,
                             @Field("area") int area,
                             @Field("conjunto") int conjunto,
                             @Field("isParada") boolean isParada);

    @FormUrlEncoded
    @POST("ordem/ordensDoDia/")
    Call<OrdensDoDia> detalheOrdemPorData(@Field("dia") String dia);

    @GET("ordem/solicitacoes/")
    Call<OrdensDoDia> ordensComoSolicitacoes();

    @GET("ordem/ativas/")
    Call<OrdensDoDia> ordensAtivas();

    @FormUrlEncoded
    @POST("ordem/alterar/")
    Call<Void> alteraOrdemStatus(@Field("id") int id,
                                 @Field("status") int status);

    @FormUrlEncoded
    @POST("ordem/remover/")
    Call<Void> removeOrdem(@Field("id") int id);

    @FormUrlEncoded
    @POST("ordem/criaCincoPorque/")
    Call<CincoPorques> criarCincoPorques(@Field("idOrdem") int idOrdem,
                                         @Field("primeiroPorque") String primeiroPorque,
                                         @Field("segundoPorque") String segundoPorque,
                                         @Field("terceiroPorque") String terceiroPorque,
                                         @Field("quartoPorque") String quartoPorque,
                                         @Field("quintoPorque") String quintoPorque
                                 );

    @GET("usuario/listaUsuarios/")
    Call<AllUsers> listaUsuarios();

    @POST("porques/detalhe/")
    Call<Porques> getPorques();

    @POST("estoque/detalhe/")
    Call<Estoque> getEstoque();



    @POST("falhas/detalhe/")
    Call<Falhas> getFalhas();

    @POST("area/detalhe/")
    Call<Areas> getAreas();

    @FormUrlEncoded
    @POST("atividadeOrdem/detalhe/")
    Call<CalendarioDetalhe> detalheCalendario(@Field("id") String id);

    @FormUrlEncoded
    @POST("ordem/detalhe/")
    Call<OrdemManutencao> detalheOrdem(@Field("id") int id);

    @FormUrlEncoded
    @POST("calendario/alteraCalendario/")
    Call<Void> alteraStatusCalendario(@Field("status") int status,@Field("id") int id);

    @POST("manutencao/detalhe/")
    Call<Manutencoes> detalheManutencao();

    @POST("conjuntos/detalhe/")
    Call<Conjuntos> detalheConjuntos();

    @FormUrlEncoded
    @POST("conjuntos/porArea/")
    Call<Conjuntos> detalheConjuntosPorArea(@Field("idArea") int id);

    @POST("subConjuntos/detalhe/")
    Call<SubConjuntos> detalheSubConjuntos();

    @FormUrlEncoded
    @POST("subConjuntos/detalhe/")
    Call<SubConjuntos> detalheSubConjuntosPorConjunto(@Field("idConjunto") int id);

    @POST("pecas/detalhe/")
    Call<Pecas> detalhePecas();

    @FormUrlEncoded
    @POST("pecas/detalhe/")
    Call<Pecas> detalhePecasPorConjunto(@Field("idConjunto") int id);

    @GET("ordem/detalheQrCode/")
    Call<QrCodeResult> detalheQrCode(@Query("code") String code);

    @FormUrlEncoded
    @POST("itenscorretivos/criar/")
    Call<Void> itensCorretivosCriar(@Field("atividade") int atividade,
                                     @Field("itemEstoque") int itemEstoque,
                                     @Field("quantidade") int quantidade);
}
