x=[ 100 101 102 103 104 105 106 107 108 109 110 111 112 113 114 115 115 116 117 118 119 120 121 122 123 124 125 126 127 128 129 130 131 132 133 134 135 136 137 138 139 140 141 142 143 144 145 146 147 148 149 150 150 151 152 152 153 154 155 156 157 158 159 160 161 162 163 164 165 166 167 168 169 170 171 172 173 174 175 176 177 178 179 180 181 182 183 184 185 186 187 188 189 190 191 192 193 194 195 195 196 196 197 197 198 198 199 200 200 201 202 203 204 205 206 207 208 209 210 211 212 213 214 215 216 217 218 219 220 221 222 223 224 225 226 227 228 229 230 231 232 233 234 235 236 237 238 239 240 241 242 243 244 245 246 247 248 249 250 250 251 252 252 252 253 253 253 254 254 254 255 256 256 257 258 259 259 260 261 262 263 264 265 266 267 268 269 270 271 272 273 274 275 276 277 278 279 280 281 282 283 284 285 286 287 288 289 290 291 292 293 294 295 296 297 298 299 300 301 302 303 304 305 306 307 308 309 310 311 312 313 314 315 316 316 317 317 318 318 318 319 319 319 319 319 319 319 319 320 320 320 321 321 322 322 323 324 325 326 327 328 329 330 331 332 333 334 335 336 337 338 339 340 341 342 343 344 345 346 347 348 349 350 351 352 353 354 355 356 357 358 359 360 361 362 363 364 365 365 365 365 365 365 365 365 365 366 367 368 369 370 371 372 373 374 375 375 376 376 376 376 377 377 378 379 380 381 382 383 384 385 386 387 387 387 387 387 386 385 384 383 382 381 380 379 378 377 376 376 376 376 376 376 376 377 378 379 380 381 382 383 384 385 386 387 388 389 390 390 390 390 390 389 388 387 386 385 384 383 382 381 380 379 378 377 377 376 376 376 376 376 377 378 379 380 381 382 383 384 385 385 386 386 386 386 386 385 385 384 383 382 381 380 379 378 377 376 375 374 373 372 371 370 369 368 368 368 368 368 369 370 371 372 373 374 375 376 377 378 379 380 381 382 382 382 383 382 382 382 381 380 379 378 377 376 375 374 373 372 371 370 369 369 368 368 368 369 369 370 371 372 373 374 375 376 377 378 379 380 381 382 382 383 383 383 382 382 381 380 379 378 377 376 375 374 373 372 371 370 369 368 367 367 367 366 367 367 367 368 369 370 371 372 373 374 375 375 375 375 375 374 373 372 371 370 369 368 367 366 365 364 364 364 364 364 364 365 365 366 367 368 369 370 371 372 373 374 374 375 375 374 374 373 372 371 370 369 368 367 366 365 364 364 363 363 363 363 364 364 365 366 367 368 369 370 371 372 373 373 374 374 374 373 373 372 371 370 369 368 367 366 365 364 363 362 361 360 360 359 359 359 360 360 361 362 363 364 365 366 367 368 369 370 371 372 373 374 375 376 377 377 377 377 377 377 376 376 375 374 373 372 371 370 369 368 367 367 367 367 367 368 368 369 370 371 372 373 374 375 376 377 378 378 378 378 377 376 375 374 373 372 371 370 369 368 367 367 366 366 366 366 367 367 368 369 370 371 372 373 374 375 376 377 377 377 377 377 376 375 374 373 372 371 370 369 368 367 366 366 366 366 366 366 367 368 369 370 371 372 373 374 375 376 377 377 378 378 378 378 378 377 377 376 375 374 373 372 371 370 369 368 367 366 365 364 363 362 362 362 362 362 363 364 365 366 367 368 369 370 371 372 372 373 373 373 373 372 372 371 370 369 368 367 366 365 364 363 362 362 362 362 362 362 363 364 365 366 367 368 369 370 371 372 373 374 375 376 377 378 379 380 381 382 383 384 385 386 387 388 389 390 391 392 393 394 395 396 397 398 399 400 401 402 403 404 405 406 406 407 408 408 409 409 410 410 410 410 411 411 411 411 411 411 411 411 410 410 410 410 409 409 408 408 407 406 405 404 403 402 401 400 399 398 397 396 395 394 393 392 391 390 389 388 387 386 385 384 383 382 381 380 379 378 377 376 375 374 373 372 371 370 370 369 368 368 367 367 366 366 366 366 366 365 365 365 365 365 366 366 366 366 367 367 367 368 368 369 370 371 372 373 374 375 376 377 378 379 380 381 382 383 384 385 386 387 388 389 390 391 392 393 394 395 396 397 398 399 400 401 402 403 404 404 405 406 406 407 407 408 408 409 409 409 409 409 409 409 409 409 409 409 408 408 408 407 407 406 406 405 405 404 403 402 401 400 399 398 397 396 395 394 393 392 391 390 389 388 387 386 385 384 383 382 381 380 380 379 378 378 378 377 ]; 
y=[ 100 100 101 101 102 103 103 104 105 106 107 108 109 110 111 113 114 116 117 118 120 121 123 124 125 126 127 128 129 129 130 131 131 132 132 133 133 134 134 135 136 136 137 138 139 140 141 142 144 145 147 148 149 151 152 154 155 157 158 160 161 162 163 164 165 166 167 168 168 169 170 170 171 171 171 172 172 173 173 173 174 174 175 175 176 176 177 178 179 180 181 182 183 184 185 187 188 190 191 192 194 195 197 198 200 201 203 204 206 207 208 210 211 212 213 214 215 216 217 218 219 219 220 220 221 221 222 222 222 223 223 223 223 223 224 224 224 224 225 225 226 226 227 227 228 228 229 230 231 232 233 234 235 236 237 239 240 242 243 244 246 247 249 250 252 253 255 256 258 259 260 262 263 265 266 268 269 271 272 273 274 275 276 277 278 279 280 280 281 281 282 282 283 283 283 284 284 284 284 284 284 284 284 284 284 284 284 284 284 284 285 285 285 286 286 287 287 288 288 289 290 290 291 292 293 294 295 296 298 299 301 302 304 305 306 308 309 311 312 314 315 317 318 320 321 322 324 325 327 328 330 331 333 334 336 337 338 340 341 343 344 345 346 347 348 349 350 351 351 352 353 353 354 354 354 355 355 356 356 356 356 356 356 356 356 356 356 355 355 354 354 354 355 355 356 357 358 359 361 362 364 365 367 368 369 371 372 374 375 376 377 377 377 377 377 377 376 376 375 375 374 373 373 372 372 372 373 373 374 375 376 377 379 380 382 383 385 386 387 389 390 390 391 391 391 391 391 390 390 389 389 388 387 387 386 386 386 386 387 387 388 389 389 389 389 389 388 388 387 387 386 386 385 385 384 384 384 385 385 386 386 387 387 387 386 386 385 385 384 384 383 382 382 382 382 382 382 382 382 382 382 381 381 380 379 379 378 378 377 377 377 378 378 379 380 381 382 383 383 383 384 383 383 382 382 381 381 380 380 379 379 378 378 379 379 380 380 381 381 382 382 381 381 380 380 379 379 378 378 377 377 377 377 377 377 378 379 379 379 379 379 378 377 377 376 376 375 375 374 374 374 374 375 375 376 377 377 377 377 377 376 376 375 375 374 374 373 373 372 372 372 372 373 374 375 375 376 376 376 376 376 375 375 374 374 373 372 372 371 371 371 371 371 371 370 370 369 369 368 368 367 366 366 366 366 366 367 368 369 370 371 373 374 376 377 378 380 381 383 384 384 385 385 385 385 384 384 383 383 382 382 381 380 380 380 380 380 380 381 382 383 384 386 387 389 390 392 393 395 396 397 398 398 399 399 399 398 398 397 397 396 396 395 395 394 394 394 394 394 395 395 396 396 396 396 396 395 395 394 394 393 393 392 392 391 391 391 392 392 393 394 395 396 397 398 398 398 398 398 397 397 396 395 395 394 394 393 393 393 393 393 394 393 393 392 392 391 391 390 390 389 389 388 388 388 389 389 390 391 392 394 395 397 398 400 401 403 404 405 406 407 407 407 407 407 407 406 405 405 404 404 403 403 402 402 402 402 403 403 404 406 407 408 410 411 413 414 416 417 418 419 420 420 421 421 421 420 420 419 419 418 418 417 417 416 416 416 416 416 417 417 418 418 417 417 416 416 415 415 414 414 413 413 412 413 413 413 414 415 416 416 417 417 417 416 416 415 415 414 413 413 412 412 412 412 412 412 413 414 415 416 418 419 420 422 423 425 426 428 429 430 430 431 431 431 431 430 429 429 428 428 427 427 426 426 426 426 426 427 427 428 429 431 432 434 435 436 437 439 440 441 441 442 443 444 444 445 445 446 446 447 447 447 448 448 448 448 448 448 448 448 448 448 447 447 446 446 445 445 444 443 443 442 442 441 441 440 440 439 439 438 437 437 436 436 435 435 434 434 433 433 432 431 431 430 430 429 429 428 428 428 428 428 427 427 427 428 428 428 428 429 429 429 430 430 431 431 432 433 433 434 435 436 437 438 439 440 442 443 444 446 447 449 450 452 453 454 456 457 459 460 462 463 465 466 468 469 470 472 473 475 476 478 479 481 482 484 485 486 488 489 491 492 493 494 495 496 497 498 498 499 500 500 501 501 502 502 503 503 503 503 504 504 504 504 504 503 503 503 502 502 501 501 500 500 499 499 498 498 497 496 496 495 495 494 494 493 493 492 492 491 490 490 489 489 488 488 487 487 486 486 485 484 484 484 483 483 483 483 483 483 483 483 483 483 483 483 483 483 482 482 481 481 480 480 479 479 478 477 477 476 ]; 
z=[ 499 499 498 498 497 497 496 496 495 495 494 494 493 493 492 492 491 491 490 490 489 489 488 488 487 487 486 486 485 485 484 484 483 483 482 482 481 481 480 480 479 479 478 478 477 477 476 476 475 475 474 474 473 473 472 472 471 471 470 470 469 469 468 468 467 467 466 466 465 465 464 464 463 463 462 462 461 461 460 460 459 459 458 458 457 457 456 456 455 455 454 454 453 453 452 452 451 451 450 450 449 449 448 448 447 447 446 446 445 445 444 444 443 443 442 442 441 441 440 440 439 439 438 438 437 437 436 436 435 435 434 434 433 433 432 432 431 431 430 430 429 429 428 428 427 427 426 426 425 425 424 424 423 423 422 422 421 421 420 420 419 419 418 418 417 417 416 416 415 415 414 414 413 413 412 412 411 411 410 410 409 409 408 408 407 407 406 406 405 405 404 404 403 403 402 402 401 401 400 400 399 399 398 398 397 397 396 396 395 395 394 394 393 393 392 392 391 391 390 390 389 389 388 388 387 387 386 386 385 385 384 384 383 383 382 382 381 381 380 380 379 379 378 378 377 377 376 376 375 375 374 374 373 373 372 372 371 371 370 370 369 369 368 368 367 367 366 366 365 365 364 364 363 363 362 362 361 361 360 360 359 359 358 358 357 357 356 356 355 355 354 354 353 353 352 352 351 351 350 350 349 349 348 348 347 347 346 346 345 345 344 344 343 343 342 342 341 341 340 340 339 339 338 338 337 337 336 336 335 335 334 334 333 333 332 332 331 331 330 330 329 329 328 328 327 327 326 326 325 325 324 324 323 323 322 322 321 321 320 320 319 319 318 318 317 317 316 316 315 315 314 314 313 313 312 312 311 311 310 310 309 309 308 308 307 307 306 306 305 305 304 304 303 303 302 302 301 301 300 300 299 299 298 298 297 297 296 296 295 295 294 294 293 293 292 292 291 291 290 290 289 289 288 288 287 287 286 286 285 285 284 284 283 283 282 282 281 281 280 280 279 279 278 278 277 277 276 276 275 275 274 274 273 273 272 272 271 271 270 270 269 269 268 268 267 267 266 266 265 265 264 264 263 263 262 262 261 261 260 260 259 259 258 258 257 257 256 256 255 255 254 254 253 253 252 252 251 251 250 250 249 249 248 248 247 247 246 246 245 245 244 244 243 243 242 242 241 241 240 240 239 239 238 238 237 237 236 236 235 235 234 234 233 233 232 232 231 231 230 230 229 229 228 228 227 227 226 226 225 225 224 224 223 223 222 222 221 221 220 220 219 219 218 218 217 217 216 216 215 215 214 214 213 213 212 212 211 211 210 210 209 209 208 208 207 207 206 206 205 205 204 204 203 203 202 202 201 201 200 200 199 199 198 198 197 197 196 196 195 195 194 194 193 193 192 192 191 191 190 190 189 189 188 188 187 187 186 186 185 185 184 184 183 183 182 182 181 181 180 180 179 179 178 178 177 177 176 176 175 175 174 174 173 173 172 172 171 171 170 170 169 169 168 168 167 167 166 166 165 165 164 164 163 163 162 162 161 161 160 160 159 159 158 158 157 157 156 156 155 155 154 154 153 153 152 152 151 151 150 150 149 149 148 148 147 147 146 146 145 145 144 144 143 143 142 142 141 141 140 140 139 139 138 138 137 137 136 136 135 135 134 134 133 133 132 132 131 131 130 130 129 129 128 128 127 127 126 126 125 125 124 124 123 123 122 122 121 121 120 120 119 119 118 118 117 117 116 116 115 115 114 114 113 113 112 112 111 111 110 110 109 109 108 108 107 107 106 106 105 105 104 104 103 103 102 102 101 101 100 100 99 99 98 98 97 97 96 96 95 95 94 94 93 93 92 92 91 91 90 90 89 89 88 88 87 87 86 86 85 85 84 84 83 83 82 82 81 81 80 80 79 79 78 78 77 77 76 76 75 75 74 74 73 73 72 72 71 71 70 70 69 69 68 68 67 67 66 66 65 65 64 64 63 63 62 62 61 61 60 60 59 59 58 58 57 57 56 56 55 55 54 54 53 53 52 52 51 51 50 50 49 49 48 48 47 47 46 46 45 45 44 44 43 43 42 42 41 41 40 40 39 39 38 38 37 37 36 36 35 35 34 34 33 33 32 32 31 31 30 30 29 29 28 28 27 27 26 26 25 25 24 24 23 23 22 22 21 21 20 20 19 19 18 18 17 17 16 16 15 15 14 14 13 13 12 12 11 11 10 10 9 9 8 8 7 7 6 6 5 5 4 4 3 3 2 2 1 1 0 0 0 ]; 
 x1=[370,370]; 
 y1=[370,370]; 
 z1=[0,0]; 
 plot3(y,x,z,'-',x1,y1,z1,'.'); 
 xlabel('Length (metres)'); 
 ylabel('Breadth (metres)'); 
 zlabel('Altitude (metres)'); 