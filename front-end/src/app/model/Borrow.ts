import { Book } from "./Book";
import { User } from "./User";

export class Borrow {
    plainUserDto: User;
    plainBookDto: Book;
    borrowDate: Date;
    returnDate: Date;
}