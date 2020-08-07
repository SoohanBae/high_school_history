import tensorflow as tf


#1단계 그래프 빌드
node1 = tf.constant(3.0, tf.float32)
node2 = tf.constant(4.0)
node3 = tf.add(node1, node2)

print("node1:", node1, "node2:",node2)
print("node3: ",node3)


#2.세션을 만들어서 실헹, 3.출력결과 확인
sess = tf.Session()
print("sess.run(node1, node2): ", sess.run([node1,node2]))
print("sess.run(node3):", sess.run(node3))