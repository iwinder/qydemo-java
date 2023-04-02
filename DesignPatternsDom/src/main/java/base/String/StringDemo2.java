package base.String;

import Utills.StringZUtils;

public class StringDemo2 {
    public static void main(String[] args) {
        String s = "<blockquote>\n" +
                "  本系列学习笔记以阅读《深度探索区块链：Hyperledger Fabric技术与应用》一书的笔记为蓝本，故默认Hyperledger Fabric 1.0，期间可能会追加最新版本的内容，到时会在里面注明。这是一个边看边写的系列，有兴趣的也可以先自行购买此书学习。\n" +
                "</blockquote>\n" +
                "\n" +
                "Hyperledger Fabric 1.0是一种通用的区块链技术，其设计目的是利用一些成熟的技术实现分布式账本技术（Distributed Ledger Techonlogy DLT）。\n" +
                "\n" +
                "超级账本采用模块化架构设计，复用通用的功能模块和接口。\n" +
                "\n" +
                "模块化的方法带来了可扩展性、灵活性等优势，会减少模块修改、升级带来的影响，能很好地利用微服务实现区块链应用系统的开发和部署。\n" +
                "\n" +
                "Hyperledger Fabric 1.0设计的几个特点：\n" +
                "\n" +
                "<table>\n" +
                "<thead>\n" +
                "<tr>\n" +
                "  <th>特点</th>\n" +
                "  <th>说明</th>\n" +
                "</tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "  <td><strong>模块插件化</strong></td>\n" +
                "  <td>1.<strong>很多的功能模块</strong>（如CA模块、共识算法、状态数据库存储、ESCC、VSCC、BCCSP等）<strong>都是可插拔的，系统提供了通用的接口和默认的实现</strong>，这满足了大多数业务的需求。<br> 2.这些模块也可以通过需求进行扩展，集成到系统中。</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "  <td><strong>充分利用容器技术</strong></td>\n" +
                "  <td>1.不仅节点使用容器做为运行环境，链码也默认运行在安全的容器中。<br> 2.应用程序或者外部系统不能直接操作链码，必须通过背书节点提供的接口转发给链码来执行。</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "  <td><strong>可扩展性</strong></td>\n" +
                "  <td>Hyperledger Fabric 1.0在0.6版本的基础上，对Peer节点的角色进行了拆分，有背书节点（Endorser）、排序服务节点（Orderer）、记账节点(Committer)等，不同角色的节点有不同的功能。<br> 节点可以加入到不同的通道（Channel）中，链码可以运行在不同的节点上，这样可以更好地提升并行执行的效率和吞吐量。</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "  <td><strong>安全性</strong></td>\n" +
                "  <td>Hyperledger Fabric 1.0提供的是授权访问的区块链网络，节点共同维护成员信息，MSP(Membership Service Provider)模块验证、授权了最终用户后才能使用区块链网络的功能。<br> 多链和多通道的设计容易实现数据隔离，也提供了应用程序和链码之间的安全通道，实现了隐私保护。</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>\n" +
                "\n" +
                "<h2>系统逻辑架构</h2>\n" +
                "\n" +
                "下图是 Hyperledger Fabric 1.0设计的系统逻辑架构图：\n" +
                "\n" +
                "<img src=\"https://github.com/iwinder/evernote/raw/master/Blockchain/Hyperledger-Fabric-biji/images/2018-12-28-22-47-21.png\" alt=\"\" />\n" +
                "\n" +
                "该图是从不同角度来划分的：上层从应用层程序的角度，提供了标准的gRPC接口，在API的基础上封装了不同语言的SDK，包括Golang、Node.js、Java、Python等，开发人员可以利用SDK开发基于区块链的应用。\n" +
                "\n" +
                "区块链强一致性要求各个节点之间达成共识需要较长的执行时间，也是采用异步通信模式进行开发的，事件模块可以在触发区块事件或者链码事件的时候执行预先定义的回调函数。\n" +
                "\n" +
                "<h3>应用程序角度</h3>\n" +
                "\n" +
                "<h4><strong>1.身份管理</strong></h4>\n" +
                "\n" +
                "<ul>\n" +
                "<li>用户注册和登录系统后，获取到用户注册证书（ECert），其他所有的操作都需要与用户证书关联的私钥进行签名。</p></li>\n" +
                "<li><p>消息接收方首先会进行签名验证，才进行后续的消息处理。</p></li>\n" +
                "<li><p>网络节点同样会收到颁发的证书，比如系统启动和网络节点管理等都会对用户身份进行认证和授权。</p></li>\n" +
                "</ul>\n" +
                "\n" +
                "<h4><strong>2.账本管理</strong></h4>\n" +
                "\n" +
                "<p><strong>授权的用户是可以查询账本数据（ledger）</strong>的，这可以通过多种方式查询，包括：根据区块号查询区块、根据区块哈希查询区块、根据交易号查询区块、根据交易号查询交易、还可以根据通道名称获取查询到的区块链信息。\n" +
                "\n" +
                "<h4><strong>3.交易管理</strong></h4>\n" +
                "\n" +
                "账本数据只能通过交易执行才能更新，应用程序通过交易管理提交交易提案（Proposal）并获取到交易背书（Endorsement）以后，再给排序服务节点提交交易，然后打包生成区块。\n" +
                "\n" +
                "SDK提供接口，利用用户证书本地生成交易号，背书节点和记账节点都会校验是否存在重复交易。\n" +
                "\n" +
                "<h4><strong>4.智能合约</strong></h4>\n" +
                "\n" +
                "实现“可编程的交易账本”（Programmable Ledger），<strong>通过链码执行提交的交易，实现基于区块链的智能合约业务逻辑</strong>。\n" +
                "\n" +
                "<strong>只有智能合约才能更新账本数据</strong>，其他模块是不能直接修改状态数据（World State）的。\n" +
                "\n" +
                "<h3>底层角度</h3>\n" +
                "\n" +
                "从1.0底层的角度来看，如何实现分布式账本技术，给应用程序提供区块链服务：\n" +
                "\n" +
                "<h4><strong>1.成员管理</strong></h4>\n" +
                "\n" +
                "MSP（Membership Service Provider）对成员管理进行了抽象。\n" +
                "\n" +
                "每个MSP都会建立一套根信任证书（Root of Truest Certificate）体系，利用PKI(Public Key Infrastructure)对成员身份进行认证，验证成员用户提交交易请求的签名。\n" +
                "\n" +
                "结合Fabric-CA或者第三方CA系统，提供成员注册功能，并对成员身份证书进行；管理，例如证书新增和撤销。\n" +
                "\n" +
                "注册的证书分为注册证书（ECert）、交易证书（TCert）和TLS证书（TLS Cert），它们分别用于用户身份、交易签名和TLS传输。\n" +
                "\n" +
                "<h4><strong>2.共识服务</strong></h4>\n" +
                "\n" +
                "在分布式节点环境下，要实现同一个链上不同节点区块的一致性，同时要确保区块里的交易有效和有序。\n" +
                "\n" +
                "共识机制由3个阶段完成：\n" +
                "\n" +
                "<ul>\n" +
                "<li>客户端向背书节点提交交易案进行签名背书；</p></li>\n" +
                "<li><p>客户端将背书后的交易提交给排序服务节点进行交易排序，生成区块和排序服务；</p></li>\n" +
                "<li><p>之后广播给记账节点验证交易后写入本地账本。</p></li>\n" +
                "</ul>\n" +
                "\n" +
                "<p>网络节点的P2P协议采用的是基于Gossip的数据分发，以同一组织为传播范围来同步数据，提升网络传输的效率。\n" +
                "\n" +
                "<h4><strong>3.链码服务</strong></h4>\n" +
                "\n" +
                "智能合约的实现依赖于安全和执行环境，确保安全的执行过程和用户数据的隔离。\n" +
                "\n" +
                "Fabric采用Docker管理普通的链码，提供安全的沙箱环境和镜像文件仓库。\n" +
                "\n" +
                "其好处是容易支持多种语言的链码，扩展性很好。\n" +
                "<img src=\"https://github.com/iwinder/evernote/raw/master/Java/concurrence/images/2018-12-26-23-32-58.png\" alt=\"\" />"+
                "\n" +
                "Docker方案的也有不足，如：对环境要求较高，占用资源较多，性能不高等，实现过程也存在与Kubernetes、Rancher等平台的兼容性问题。\n" +
                "\n" +
                "<h4><strong>4.安全和密码服务</strong></h4>\n" +
                "\n" +
                "Fabric 1.0专门定义了一个BCCSP(BlockChain Cryptographic Service Provider)，使其实现秘钥生成、哈喜运算、签名验签、加密解密等基础功能。\n" +
                "\n" +
                "BBSCP是一个抽象接口，默认是软视线的国标算法，目前社区和较多的厂商都在实现国密的算法和HSM(Hardware Security Module)。\n";

        String img = StringZUtils.getMatcher("/<img [^>]*\\bsrc=['\"]([^'\"]+)[^>]*>/gi", s);
    }
}
