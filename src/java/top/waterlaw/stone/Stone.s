even = 0
odd = 0
i = 1
while i < 10 {
   if i % 2 == 0 {       // even number?
      even = even + i
   } else {
      odd = odd + i
   }
   i = i + 1
}
even + odd

inc = fun (x) { x + 1 }
inc(3)

def fib (n) {
    if n < 2 {
        n
    } else {
        fib(n - 1) + fib(n - 2)
    }
}
t = currentTime()
fib 15
print currentTime() - t + " msec"
