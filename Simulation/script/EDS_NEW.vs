<vscript> 
<config>  </config>

 <function>deploy nodes(300,70,40)</function>
<function>delay(1000)</function>
 <function>setNodeInitialEnergy(0.5)</function>
<function>delay(1000)</function>

<function>deploy bs(100,100)</function>
<function>delay(5000)</function>

<function>deploy bs(600,600)</function>
<function>delay(5000)</function>

<function>broadcast bs(0)</function>
<function>delay(1000)</function>

<function>broadcast bs(1)</function>
<function>delay(1000)</function>


<function>broadcastForClustering(0,100)</function>
<function>delay(5000)</function>
<function>connectionShowable(false)</function>
<function>interClusterConnectionShowable(true)</function>
<function>delay(1000)</function>
<function>set_cluster_head</function>
<function>delay(1000)</function>

<function>update_bs_cluster_list</function>
<function>delay(1000)</function>

<function>broadcastClsuterHeadStatus</function>
<function>delay(5000)</function>
<function>send_data_to_cluster_head</function>
<function>delay(5000)</function>
<function>get_data_directly_from_cluster_head</function>
<function>delay(5000)</function>

<function>startEDSRoundsWithMultihop</function>
<function>delay(5000)</function>

 </vscript>